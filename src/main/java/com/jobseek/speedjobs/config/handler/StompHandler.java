package com.jobseek.speedjobs.config.handler;

import com.jobseek.speedjobs.util.JwtUtil;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StompHandler implements ChannelInterceptor {

	private final JwtUtil jwtUtil;

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
		if (StompCommand.CONNECT.equals(accessor.getCommand())) {
			String header = accessor.getFirstNativeHeader("Authorization");
			String token = Objects.requireNonNull(header).substring(7);
			jwtUtil.validateToken(token);
		}
		return message;
	}
}
