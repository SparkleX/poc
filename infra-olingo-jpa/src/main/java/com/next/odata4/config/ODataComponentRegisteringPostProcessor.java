package com.next.odata4.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.next.odata4.annotation.ODataEntitySet;

public class ODataComponentRegisteringPostProcessor implements BeanFactoryPostProcessor, ApplicationContextAware {

	private final Set<String> packagesToScan;

	private ApplicationContext applicationContext;
	
	public static List<String> services =  new ArrayList<String>(); 

	ODataComponentRegisteringPostProcessor(Set<String> packagesToScan) {
		this.packagesToScan = packagesToScan;
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		ClassPathScanningCandidateComponentProvider componentProvider = createComponentProvider();
		for (String packageToScan : this.packagesToScan) {
			scanPackage(componentProvider, packageToScan);
		}
	}

	private void scanPackage(ClassPathScanningCandidateComponentProvider componentProvider, String packageToScan) {
		for (BeanDefinition candidate : componentProvider.findCandidateComponents(packageToScan)) {
			if (candidate instanceof ScannedGenericBeanDefinition) {
				String name = candidate.getBeanClassName();
				try {
					Class clazz = Class.forName(name);
					System.out.println(clazz);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}						
				services.add(name);
			/*	BeanDefinitionBuilder builder = BeanDefinitionBuilder
						.rootBeanDefinition(ODataRegistrationBean.class);
				builder.addPropertyValue("name", name);
				BeanDefinitionRegistry registry = (BeanDefinitionRegistry) this.applicationContext;
				registry.registerBeanDefinition(name, builder.getBeanDefinition());*/
			}
		}
	}

	private ClassPathScanningCandidateComponentProvider createComponentProvider() {
		ClassPathScanningCandidateComponentProvider componentProvider = new ClassPathScanningCandidateComponentProvider(
				false);
		componentProvider.setEnvironment(this.applicationContext.getEnvironment());
		componentProvider.setResourceLoader(this.applicationContext);
		componentProvider.addIncludeFilter(new AnnotationTypeFilter(ODataEntitySet.class));
		return componentProvider;
	}

	Set<String> getPackagesToScan() {
		return Collections.unmodifiableSet(this.packagesToScan);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
