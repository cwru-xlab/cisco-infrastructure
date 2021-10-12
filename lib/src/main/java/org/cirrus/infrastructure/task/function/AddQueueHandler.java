package org.cirrus.infrastructure.task.function;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.function.Consumer;
import org.cirrus.infrastructure.task.resource.Resource;
import org.cirrus.infrastructure.task.util.MappingUtil;

public class AddQueueHandler implements RequestHandler<String, String> {

  private static final Consumer<Throwable> logger = DaggerFunctionComponent.create().getLogger();

  @Override
  public String handleRequest(String input, Context context) {
    AddQueueInput mappedInput = mapInput(input);
    String functionId = getFunctionId(mappedInput);
    String queueId = getQueueId(mappedInput);
    addQueue(functionId, queueId);
    AddQueueOutput output = createOutput(mappedInput);
    return mapOutput(output);
  }

  private AddQueueInput mapInput(String input) {
    return MappingUtil.read(input, AddQueueInput.class, logger);
  }

  private String getFunctionId(AddQueueInput input) {
    return getResourceId(input, Resource.FUNCTION);
  }

  private String getQueueId(AddQueueInput input) {
    return getResourceId(input, Resource.QUEUE);
  }

  private void addQueue(String functionId, String queueId) {
    AddQueueCommand.create(functionId, queueId).run();
  }

  private AddQueueOutput createOutput(AddQueueInput input) {
    return AddQueueOutput.newBuilder().addAllOutputs(input.getOutputs()).build();
  }

  private String mapOutput(AddQueueOutput output) {
    return MappingUtil.write(output, logger);
  }

  private String getResourceId(AddQueueInput input, Resource type) {
    return input.getTypedOutputs().get(type).getResourceId();
  }
}