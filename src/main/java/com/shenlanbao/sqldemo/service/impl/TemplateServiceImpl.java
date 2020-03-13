package com.shenlanbao.sqldemo.service.impl;

import com.shenlanbao.sqldemo.mapper.TemplateMapper;
import com.shenlanbao.sqldemo.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateMapper templateMapper;

    @Override
    public Integer getTitleId(String title, Integer tabId) {
        return templateMapper.getTitleId(title, tabId);
    }
}
