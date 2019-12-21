package com.example;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;

class PongActor extends AbstractLoggingActor {

  public static Props props() {
    return Props.create(PongActor.class, PongActor::new);
  }

  @Override
   public Receive createReceive() {
      return receiveBuilder()
        .match(PingMessage.class, t -> {
          // log().info("In PongActor - received message: {}", t.text);
          sender().tell(new PongMessage("pong"), self());
        })
        .build();
    }

  static class PongMessage {
    public PongMessage(String text) {
      this.text = text;
    }

    String text;
  }
}



