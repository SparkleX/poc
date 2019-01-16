package com.next.odata4.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;
import javax.servlet.http.PushBuilder;

public class HttpServletRequestWapper implements HttpServletRequest
{
	HttpServletRequest delegate;
	private String path;
	
	HttpServletRequestWapper(HttpServletRequest value, String path)
	{
		this.delegate = value;
		this.path = path;
	}
	public String getServletPath() {
		return path;
	}
	public Object getAttribute(String name) {
		return delegate.getAttribute(name);
	}
	public String getAuthType() {
		return delegate.getAuthType();
	}
	public Cookie[] getCookies() {
		return delegate.getCookies();
	}
	public Enumeration<String> getAttributeNames() {
		return delegate.getAttributeNames();
	}
	public long getDateHeader(String name) {
		return delegate.getDateHeader(name);
	}
	public String getCharacterEncoding() {
		return delegate.getCharacterEncoding();
	}
	public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
		delegate.setCharacterEncoding(env);
	}
	public String getHeader(String name) {
		return delegate.getHeader(name);
	}
	public int getContentLength() {
		return delegate.getContentLength();
	}
	public Enumeration<String> getHeaders(String name) {
		return delegate.getHeaders(name);
	}
	public long getContentLengthLong() {
		return delegate.getContentLengthLong();
	}
	public String getContentType() {
		return delegate.getContentType();
	}
	public ServletInputStream getInputStream() throws IOException {
		return delegate.getInputStream();
	}
	public Enumeration<String> getHeaderNames() {
		return delegate.getHeaderNames();
	}
	public String getParameter(String name) {
		return delegate.getParameter(name);
	}
	public int getIntHeader(String name) {
		return delegate.getIntHeader(name);
	}
	public HttpServletMapping getHttpServletMapping() {
		return delegate.getHttpServletMapping();
	}
	public Enumeration<String> getParameterNames() {
		return delegate.getParameterNames();
	}
	public String getMethod() {
		return delegate.getMethod();
	}
	public String getPathInfo() {
		return delegate.getPathInfo();
	}
	public String[] getParameterValues(String name) {
		return delegate.getParameterValues(name);
	}
	public Map<String, String[]> getParameterMap() {
		return delegate.getParameterMap();
	}
	public String getPathTranslated() {
		return delegate.getPathTranslated();
	}
	public String getProtocol() {
		return delegate.getProtocol();
	}
	public PushBuilder newPushBuilder() {
		return delegate.newPushBuilder();
	}
	public String getScheme() {
		return delegate.getScheme();
	}
	public String getServerName() {
		return delegate.getServerName();
	}
	public String getContextPath() {
		return delegate.getContextPath();
	}
	public int getServerPort() {
		return delegate.getServerPort();
	}
	public BufferedReader getReader() throws IOException {
		return delegate.getReader();
	}
	public String getQueryString() {
		return delegate.getQueryString();
	}
	public String getRemoteUser() {
		return delegate.getRemoteUser();
	}
	public String getRemoteAddr() {
		return delegate.getRemoteAddr();
	}
	public boolean isUserInRole(String role) {
		return delegate.isUserInRole(role);
	}
	public String getRemoteHost() {
		return delegate.getRemoteHost();
	}
	public Principal getUserPrincipal() {
		return delegate.getUserPrincipal();
	}
	public void setAttribute(String name, Object o) {
		delegate.setAttribute(name, o);
	}
	public String getRequestedSessionId() {
		return delegate.getRequestedSessionId();
	}
	public String getRequestURI() {
		return delegate.getRequestURI();
	}
	public void removeAttribute(String name) {
		delegate.removeAttribute(name);
	}
	public StringBuffer getRequestURL() {
		return delegate.getRequestURL();
	}
	public Locale getLocale() {
		return delegate.getLocale();
	}
	public Enumeration<Locale> getLocales() {
		return delegate.getLocales();
	}

	public boolean isSecure() {
		return delegate.isSecure();
	}
	public RequestDispatcher getRequestDispatcher(String path) {
		return delegate.getRequestDispatcher(path);
	}
	public HttpSession getSession(boolean create) {
		return delegate.getSession(create);
	}
	public HttpSession getSession() {
		return delegate.getSession();
	}
	@SuppressWarnings("deprecation")
	public String getRealPath(String path) {
		return delegate.getRealPath(path);
	}
	public String changeSessionId() {
		return delegate.changeSessionId();
	}
	public int getRemotePort() {
		return delegate.getRemotePort();
	}
	public boolean isRequestedSessionIdValid() {
		return delegate.isRequestedSessionIdValid();
	}
	public String getLocalName() {
		return delegate.getLocalName();
	}
	public boolean isRequestedSessionIdFromCookie() {
		return delegate.isRequestedSessionIdFromCookie();
	}
	public String getLocalAddr() {
		return delegate.getLocalAddr();
	}
	public boolean isRequestedSessionIdFromURL() {
		return delegate.isRequestedSessionIdFromURL();
	}
	public int getLocalPort() {
		return delegate.getLocalPort();
	}
	public ServletContext getServletContext() {
		return delegate.getServletContext();
	}
	@SuppressWarnings("deprecation")
	public boolean isRequestedSessionIdFromUrl() {
		return delegate.isRequestedSessionIdFromUrl();
	}
	public AsyncContext startAsync() throws IllegalStateException {
		return delegate.startAsync();
	}
	public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
		return delegate.authenticate(response);
	}
	public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
			throws IllegalStateException {
		return delegate.startAsync(servletRequest, servletResponse);
	}
	public boolean isAsyncStarted() {
		return delegate.isAsyncStarted();
	}
	public boolean isAsyncSupported() {
		return delegate.isAsyncSupported();
	}
	public AsyncContext getAsyncContext() {
		return delegate.getAsyncContext();
	}
	public void login(String username, String password) throws ServletException {
		delegate.login(username, password);
	}
	public DispatcherType getDispatcherType() {
		return delegate.getDispatcherType();
	}
	public void logout() throws ServletException {
		delegate.logout();
	}
	public Collection<Part> getParts() throws IOException, ServletException {
		return delegate.getParts();
	}
	public Part getPart(String name) throws IOException, ServletException {
		return delegate.getPart(name);
	}
	public <T extends HttpUpgradeHandler> T upgrade(Class<T> httpUpgradeHandlerClass)
			throws IOException, ServletException {
		return delegate.upgrade(httpUpgradeHandlerClass);
	}
	public Map<String, String> getTrailerFields() {
		return delegate.getTrailerFields();
	}
	public boolean isTrailerFieldsReady() {
		return delegate.isTrailerFieldsReady();
	}

}
