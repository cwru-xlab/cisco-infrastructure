package org.cirrus.infrastructure.task.topic;

import java.util.function.Consumer;
import org.cirrus.infrastructure.task.Command;
import org.cirrus.infrastructure.task.resource.CreateResourceTask;
import org.cirrus.infrastructure.task.resource.Resource;

public final class CreateTopicTask extends CreateResourceTask {

  private static final Resource TYPE = Resource.TOPIC;
  private static final Command<String> command = CreateTopic.create();
  private static final Consumer<Throwable> logger = DaggerTopicComponent.create().newLogger();

  public CreateTopicTask() {
    super(TYPE, command, logger);
  }
}
