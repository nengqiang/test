package com.hnq.test.generate;

import com.github.cage.Cage;
import com.hnq.test.generator.MyFontGenerator;
import com.hnq.test.generator.MyPainter;
import com.hnq.test.generator.MyTokenGenerator;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Cage
 *
 * @author henengqiang
 * @date 2018/12/4
 */
@Component
public class CageGenerate {

    @Autowired
    private CageConfig config;

    @Autowired
    private MyFontGenerator myFontGenerator;

    @Autowired
    private MyTokenGenerator myTokenGenerator;

    private MyPainter myPainter = new MyPainter();

    private Cage cage = new Cage(myPainter, myFontGenerator,null,null,null, myTokenGenerator,null);

    public void generateVerifyCodePic() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        String token = cage.getTokenGenerator().next().substring(0, 4);
        try {
            cage.draw(token, os);
            os.close();

            String file = config.getFilePath() + File.separator + token + "_" + System.currentTimeMillis() + ".png";
            FileUtils.writeByteArrayToFile(new File(file), os.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
