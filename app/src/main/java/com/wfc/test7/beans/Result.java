package com.wfc.test7.beans;

import java.util.List;

/**
 * Created by wangfengchen on 2017/1/9.
 */

public class Result<T> {
    public int status;
    public String msg;
    public T detail;
    public List<T> results;
}
