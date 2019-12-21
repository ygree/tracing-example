package com.example;


import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;


class PingActor extends AbstractLoggingActor {
	ActorRef pongActor;
	int counter;
	int counterIteration = 1000000000;  //Increase or decrease its value to adjust load on the actors.

	public PingActor(ActorRef pongActor) {
		this.pongActor = pongActor;
	}

	public static Props props(ActorRef pongActor) {
    return Props.create(PingActor.class, () -> new PingActor(pongActor));
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
			.match(Initialize.class, t -> {
				log().info("In PingActor - starting ping-pong");
				pongActor.tell(new PingMessage("ping"), self());
			})
			.match(PongActor.PongMessage.class, t -> {
				log().info("In PingActor - received message: {}", t.text);
				counter += 1;
				if (counter == counterIteration) this.context().system().terminate();
				else sender().tell(new PingMessage("ping"), self());
			}).build();
	}

	static class Initialize{
		public static final Initialize INSTANCE = new Initialize();
	}

}



class PingMessage {
	String text;

	public PingMessage(String text) {
		this.text = text;
	}
}
