package org.cirrus.infrastructure.handler;

import org.cirrus.infrastructure.handler.api.ApiCommand;
import org.cirrus.infrastructure.handler.api.ApiRequest;
import org.cirrus.infrastructure.handler.api.ApiResponse;

public class DeleteNodeHandler extends ApiHandler {

  private static final ApiCommand command = DeleteNodeApi.create();

  @Override
  public ApiResponse run(ApiRequest request) {
    return command.run(request);
  }
}
