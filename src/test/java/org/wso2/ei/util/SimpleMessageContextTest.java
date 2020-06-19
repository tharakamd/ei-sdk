package org.wso2.ei.util;

import org.apache.synapse.MessageContext;
import org.apache.synapse.commons.json.JsonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@PrepareForTest({JsonUtil.class})
class SimpleMessageContextTest extends PowerMockTestCase {

    @Mock
    private MessageContext messageContextMock;
    private SimpleMessageContext simpleMessageContext;

    @BeforeEach
    void setUp() {

        simpleMessageContext = new SimpleMessageContext(messageContextMock);
    }

    @Test
    public void getJsonStringTest() {

        mockStatic(JsonUtil.class);
        when(JsonUtil.jsonPayloadToString(any())).thenReturn("a");

        final String result = simpleMessageContext.getJsonString();

        Assertions.assertEquals("a", result);

    }
}