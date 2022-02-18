package org.cirrus.infrastructure.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;

public class CreateNodeHandler implements RequestHandler<APIGatewayV2HTTPEvent, String> {

  @Override
  public String handleRequest(APIGatewayV2HTTPEvent event, Context context) {
    return CreateNodeCommand.getInstance().runFromString(event.getBody());
  }
}
