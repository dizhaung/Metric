package org.loxf.metric.filter;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.lang.StringUtils;
import org.loxf.metric.base.constants.RateLimitType;
import org.loxf.metric.common.constants.ResultCodeEnum;
import org.loxf.metric.common.dto.BaseResult;
import org.loxf.metric.controller.RateLimitMapBean;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 流量控制器
 * Created by hutingting on 2017/7/22.
 */
public class RateLimitFilter implements Filter {
    private String[] ratelimit_uri_array;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String ratelimit_uri= filterConfig.getInitParameter("RATELIMIT_URI");
        if(StringUtils.isBlank(ratelimit_uri)){
            return;
        }
        ratelimit_uri_array = ratelimit_uri.split(",");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        // 设置编码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        boolean needValidate = false;
        // 判断请求URI是否在配置的需要登录验证的URI列表中
        for (int i = 0; i < ratelimit_uri_array.length; i++) {
            String sUri = ratelimit_uri_array[i];
            if (req.getRequestURI().contains(//getSMS
                    req.getContextPath() + sUri)) {
                needValidate = true;
                break;
            }
        }
        //如果不需要拦截请求,PASS
        if (needValidate) {
            String rateLimitType=req.getRequestURI();
            RateLimiter limiter;
            switch (rateLimitType){
                case "/authen/getSMSValidateCode":
                    limiter=RateLimitMapBean.RATELIMITMAPSTATIC.get(RateLimitType.REGISTERCODE);
                    if(limiter!=null){
                        if(!limiter.tryAcquire()){ //未请求到limiter则立即返回false
                            returnBusy(resp);
                            return;
                        }
                    }
                    break;
                case RateLimitType.LOGINCODE:
                    limiter=RateLimitMapBean.RATELIMITMAPSTATIC.get(RateLimitType.LOGINCODE);
                    if(limiter!=null){
                        if(!limiter.tryAcquire()){ //未请求到limiter则立即返回false
                            returnBusy(resp);
                            return;
                        }
                    }
                    break;
                case RateLimitType.MODIFYPWDCODE:
                    limiter=RateLimitMapBean.RATELIMITMAPSTATIC.get(RateLimitType.MODIFYPWDCODE);
                    if(limiter!=null){
                        if(!limiter.tryAcquire()){ //未请求到limiter则立即返回false
                            returnBusy(resp);
                            return;
                        }
                    }
                    break;
                case RateLimitType.CHILDSENDPWDCODE:
                    limiter=RateLimitMapBean.RATELIMITMAPSTATIC.get(RateLimitType.CHILDSENDPWDCODE);
                    if(limiter!=null){
                        if(!limiter.tryAcquire()){ //未请求到limiter则立即返回false
                            returnBusy(resp);
                            return;
                        }
                    }
                    break;
                default:
                    return;
            }
        }

        chain.doFilter(request, response);
    }

    private void returnBusy(HttpServletResponse resp){
        try {
            resp.getWriter().write(JSON.toJSONString(
                    new BaseResult(ResultCodeEnum.SYSTEM_BUSY.getCode(), ResultCodeEnum.SYSTEM_BUSY.getCodeMsg())));
        }catch (Exception e){

        }
    }

    @Override
    public void destroy() {

    }
}
