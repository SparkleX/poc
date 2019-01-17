package com.next.odata4;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.next.odata4.annotation.ODataEntitySet;
import com.next.odata4.config.ODataCrudService;

import gen.table.BmoOCRD;

@Service
@Transactional
@ODataEntitySet(name = "OCRDCollection")
public class ServiceOCRD extends BaseService<BmoOCRD, Integer> implements ODataCrudService<BmoOCRD, Integer>
{

}
