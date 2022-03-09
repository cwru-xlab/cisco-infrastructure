package org.cirrus.infrastructure.handler;

import javax.inject.Inject;
import org.cirrus.infrastructure.handler.api.ApiCommand;
import org.cirrus.infrastructure.handler.api.ApiRequest;
import org.cirrus.infrastructure.handler.api.ApiResponse;
import org.cirrus.infrastructure.handler.api.HttpStatus;
import org.cirrus.infrastructure.handler.exception.CirrusException;

public class PublishCodeApi implements ApiCommand {

  private static final PublishCodeComponent component = DaggerPublishCodeComponent.create();
  private final Command<?, ?> command;

  @Inject
  public PublishCodeApi(PublishCodeCommand command) {
    this.command = command;
  }

  public static PublishCodeApi create() {
    return component.api();
  }

  @Override
  public ApiResponse run(ApiRequest request) {
    String body;
    int status;
    try {
      body = command.runFromString(request.body());
      status = HttpStatus.OK;
    } catch (CirrusException exception) {
      body = exception.getLocalizedMessage();
      status = HttpStatus.BAD_REQUEST;
    }
    return ApiResponse.of(body, status);
  }
}