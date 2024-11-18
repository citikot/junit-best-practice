/**
 * This portion of the source code is copyrighted by Thumbtack Technology LLC,
 * all rights reserved, and is subject to the terms of a license agreement
 * in which it constitutes “Pre-existing work” or "Licensed Product".
 */
package com.lineate.traineeship.junit.clients;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.SingleRootFileSource;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.lineate.traineeship.junit.TestBase;

import java.io.Closeable;
import java.io.IOException;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

/**
 * Base DAO test.
 */
public abstract class WireMockTestBase extends TestBase {

    protected CloseableWireMockServer startWireMock(String stubsPath) {
        return new CloseableWireMockServer(
            wireMockConfig()
                .dynamicPort()
                .fileSource(
                    new SingleRootFileSource(
                        getClass().getResource(stubsPath).getPath().replace("file:", "")
                    )
                )
        );
    }

    public class CloseableWireMockServer extends WireMockServer implements Closeable {

        CloseableWireMockServer(WireMockConfiguration wireMockConfiguration) {
            super(wireMockConfiguration);
            start();
        }

        @Override
        public void close() throws IOException {
            stop();
        }
    }

}
