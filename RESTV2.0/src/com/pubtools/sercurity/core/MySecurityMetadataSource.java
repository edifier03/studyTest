package com.pubtools.sercurity.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.pubtools.sercurity.bean.Resources;
import com.pubtools.sercurity.dao.ResourcesDao;


public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {  
    //��spring����  
    public MySecurityMetadataSource() {  
        this.resourcesDao = new ResourcesDao();
        loadResourceDefine();  
    }  
  
    private ResourcesDao resourcesDao;  
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;  
  
    public ResourcesDao getResourcesDao() {  
        return resourcesDao;  
    }  
  
    public void setResourcesDao(ResourcesDao resourcesDao) {  
        this.resourcesDao = resourcesDao;  
    }  
  
    public Collection<ConfigAttribute> getAllConfigAttributes() {  
        // TODO Auto-generated method stub  
        return null;  
    }  
  
    public boolean supports(Class<?> clazz) {  
        // TODO Auto-generated method stub  
        return true;  
    }  
    //����������Դ��Ȩ�޵Ĺ�ϵ  
    /**
     * ϵͳ����ʱ���أ�
     * ҵ���߼����Դ����ݿ��ж�ȡ���е�Ȩ�޹�ϵ�����Ҵ洢��redis��������������
     */
    private void loadResourceDefine() {  
        if(resourceMap == null) {  
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();  
            List<Resources> resources = this.resourcesDao.findAll();  
            for (Resources resource : resources) {  
                Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();  
                                //��Ȩ������װΪSpring��security Object  
                ConfigAttribute configAttribute = new SecurityConfig(resource.getName());  
                configAttributes.add(configAttribute);  
                resourceMap.put(resource.getUrl(), configAttributes);  
            }  
        }  
          
        Set<Entry<String, Collection<ConfigAttribute>>> resourceSet = resourceMap.entrySet();  
        Iterator<Entry<String, Collection<ConfigAttribute>>> iterator = resourceSet.iterator();  
          
    }  
    //������������Դ����Ҫ��Ȩ��  
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {  
          
        String requestUrl = ((FilterInvocation) object).getRequestUrl();  
        System.out.println("requestUrl is " + requestUrl);  
        if(resourceMap == null) {  
            loadResourceDefine();  
        }  
        return resourceMap.get(requestUrl);  
    }  
  
}  