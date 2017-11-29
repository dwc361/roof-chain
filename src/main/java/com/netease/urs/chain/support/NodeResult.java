package com.netease.urs.chain.support;

/**
 * 返回结果
 * <p>
 * state 代表本次执行的状态:
 * 返回结果状态两种表达方式
 * 1.返回字符串
 * 字符串粗略的表达了执行结果 success:成功, fail:失败, error:错误
 * 2.返回码
 * 返回码可以精确的区分各种执行结果  2xx:成功, 4xx:失败, 5xx:错误
 * xx的位数可以根据项目自行定义
 */
public class NodeResult {

    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final String ERROR = "error";

    public static final String DEFAULT_SUCCESS_CODE = "200";
    public static final String DEFAULT_FAIL_CODE = "400";
    public static final String DEFAULT_ERROR_CODE = "500";

    private String next;
    private String state;
    private String message;

    public NodeResult(String next) {
        this.state = SUCCESS;
        this.next = next;
    }

    public NodeResult(String state, String next, String message) {
        this.next = next;
        this.state = state;
        this.message = message;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
