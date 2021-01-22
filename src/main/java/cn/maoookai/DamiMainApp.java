package cn.maoookai;

import cn.maoookai.listener.MainListener;
import cn.maoookai.util.FileReadUtil;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DamiMainApp {

    public static void main(String[] args) throws Exception {
        InputStream in = new FileInputStream("config.properties");
        Properties properties = new Properties();
        properties.load(in);
        Bot dami = BotFactory.INSTANCE.newBot(Long.parseLong(properties.getProperty("qq.account")), properties.getProperty("qq.password"), new BotConfiguration() {{
            setProtocol(MiraiProtocol.ANDROID_PAD);
            fileBasedDeviceInfo();
            File oldLog = new File(properties.getProperty("mirai.log.path"));
            System.out.println("��ǰ��־Ŀ¼Ϊ" + oldLog.getAbsolutePath() + "������־ɾ�����Ϊ" + oldLog.delete());
            redirectBotLogToFile(new File(properties.getProperty("mirai.log.path")));
        }});
        dami.login();

        new MainListener().initListener();
    }
}
