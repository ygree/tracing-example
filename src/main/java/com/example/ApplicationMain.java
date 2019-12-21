package com.example;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Terminated;

class ApplicationMain extends AbstractLoggingActor {

  @Override
  public Receive createReceive() {
    return receiveBuilder().match(Terminated.class, t -> {
      log().error("Shutting down, because actor {} terminated!", t.getActor().path());
      getContext().become(emptyBehavior());
    }).build();
  }

  public static void main(String[] args) {
    ActorSystem actorSystem = ActorSystem.create("MyActorSystem");
    ActorRef pong = actorSystem.actorOf(PongActor.props(), "pongActor");
    ActorRef ping = actorSystem.actorOf(PingActor.props(pong), "pingActor");
    ping.tell(PingActor.Initialize.INSTANCE, ping);

    actorSystem.log().info("System started");
//    Await.ready(actorSystem.whenTerminated(), Duration.seconds(1));
  }
}