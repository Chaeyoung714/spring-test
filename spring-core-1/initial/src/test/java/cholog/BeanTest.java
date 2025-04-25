package cholog;

import cholog.bean.AutowiredBean;
import cholog.bean.SpringBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

import static cholog.utils.ContextUtils.getApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;

public class BeanTest {

    @Test
    void registerBean() {
        ApplicationContext context = getApplicationContext();
        SpringBean springBean = context.getBean("springBean", SpringBean.class);
        assertThat(springBean).isNotNull();
    }

    @Test
    void autowiredBean() {
        ApplicationContext context = getApplicationContext();
        AutowiredBean autowiredBean = context.getBean("autowiredBean", AutowiredBean.class);
        assertThat(autowiredBean.sayHello()).isEqualTo("Hello");
    }

    @Test
    void autowiredBean2() {
        StaticApplicationContext context = new StaticApplicationContext();
        context.registerBeanDefinition("springBean", new RootBeanDefinition(SpringBean.class));

        BeanDefinition helloDef = new RootBeanDefinition(AutowiredBean.class);
        // 아이디가 printer 인 빈을 찾아서 printer 프로퍼티에 DI
        helloDef.getPropertyValues().addPropertyValue("springBean", new RuntimeBeanReference("springBean"));
        context.registerBeanDefinition("autowiredBean", helloDef);

        AutowiredBean autowiredBean = context.getBean("autowiredBean", AutowiredBean.class);
        assertThat(autowiredBean.sayHello()).isEqualTo("Hello");
    }
}
