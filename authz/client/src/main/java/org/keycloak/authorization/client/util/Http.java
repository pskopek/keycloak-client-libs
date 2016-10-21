/*
 *  Copyright 2016 Red Hat, Inc. and/or its affiliates
 *  and other contributors as indicated by the @author tags.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.keycloak.authorization.client.util;

import org.apache.http.client.methods.RequestBuilder;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.authorization.client.representation.ServerConfiguration;

import java.net.URI;

/**
 * @author <a href="mailto:psilva@redhat.com">Pedro Igor</a>
 */
public class Http {

    private final Configuration configuration;
    private ServerConfiguration serverConfiguration;

    public Http(Configuration configuration) {
        this.configuration = configuration;
    }

    public <R> HttpMethod<R> get(String path) {
        return method(RequestBuilder.get().setUri(this.serverConfiguration.getIssuer() + path));
    }

    public <R> HttpMethod<R> get(URI path) {
        return method(RequestBuilder.get().setUri(path));
    }

    public <R> HttpMethod<R> post(URI path) {
        return method(RequestBuilder.post().setUri(path));
    }

    public <R> HttpMethod<R> post(String path) {
        return method(RequestBuilder.post().setUri(this.serverConfiguration.getIssuer() + path));
    }

    public <R> HttpMethod<R> delete(String path) {
        return method(RequestBuilder.delete().setUri(this.serverConfiguration.getIssuer() + path));
    }

    private <R> HttpMethod<R> method(RequestBuilder builder) {
        return new HttpMethod(this.configuration, builder);
    }

    public void setServerConfiguration(ServerConfiguration serverConfiguration) {
        this.serverConfiguration = serverConfiguration;
    }
}
