package cn.maoookai.listener;

import cn.maoookai.handler.GroupMessageEventHandler;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.events.GroupMessageEvent;

import java.io.IOException;

public class GroupMessageListener {

    public GroupMessageListener(Bot bot) {
        Listener<GroupMessageEvent> groupMessageEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, groupMessageEvent -> {
            try {
                new GroupMessageEventHandler().onMessage(groupMessageEvent, bot);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        groupMessageEventListener.start();
    }
}