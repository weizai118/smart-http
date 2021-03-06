/*
 * Copyright (c) 2018, org.smartboot. All rights reserved.
 * project name: smart-socket
 * file name: HttpHandle.java
 * Date: 2018-02-15
 * Author: sandao
 */

package org.smartboot.http.server.handle;

import org.smartboot.http.server.http11.Http11Request;
import org.smartboot.http.server.http11.HttpResponse;

import java.io.IOException;

/**
 * @author 三刀
 * @version V1.0 , 2018/2/6
 */
public abstract class HttpHandle {

    private HttpHandle nextHandle;

    public abstract void doHandle(Http11Request request, HttpResponse response) throws IOException;

    protected final void doNext(Http11Request request, HttpResponse response) throws IOException {
        if (nextHandle != null) {
            nextHandle.doHandle(request, response);
        }
    }

    /**
     * 添加CheckFilter至末尾
     *
     * @param nextHandle
     * @return
     */
    public final HttpHandle next(HttpHandle nextHandle) {
        HttpHandle httpHandle = this;
        while (httpHandle.nextHandle != null) {
            httpHandle = httpHandle.nextHandle;
        }
        httpHandle.nextHandle = nextHandle;
        return this;
    }
}
