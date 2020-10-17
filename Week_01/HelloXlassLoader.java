import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义 ClassLoader，加载处理过的 class 文件
 * Created by xiushan on 2020/10/17.
 */
public class HelloXlassLoader extends ClassLoader {
    public static void main(String[] args) {
        try {
            Class helloXlass = new HelloXlassLoader().findClass("Hello");  // 加载 Hello 类
            Object helloObj = helloXlass.newInstance();
            Method method = helloXlass.getMethod("hello");
            method.invoke(helloObj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File(HelloXlassLoader.class.getResource("/Hello.xlass").getPath());
        long len = file.length();
        byte[] bytes = new byte[(int)len];
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fis.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return super.findClass(name);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte)(255 - bytes[i]);
        }
        return defineClass(name, bytes, 0, bytes.length);
    }
}
