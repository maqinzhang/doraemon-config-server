# doraemon-config-server
Automatically exported from code.google.com/p/doraemon-config-server
一套监控、服务降级、统一配置集成系统.
背景：
一方面，在SOA服务化过程中，我们将业务封装成业务接口的方式，以分而治之的思想，对各个应用系统进行分拆，提升各个应用的独立性，使得系统伸缩性更加好。 在整体架构上提升系统的灵活性和性能的扩展等等。正是因为这种系统服务化的趋势，使得各个系统之家存在依赖（直接依赖或者间接依赖），也存在强依赖和弱依赖的关系。 特别在互联网的应用中，有时候系统的并发和吞吐量是在某一个时刻或者某个时间段是激增的。往往一个系统出现功能或者性能的问题，就可能导致连锁反应，影响到其它系统， 从而把整个系统拖垮，这样类似于蝴蝶效应的现象。正因为这样，我们必须要保证系统高可用的情况下，更加灵活的监控和控制我们的系统，对服务进行分级管理，对一些优先级比较低， 依赖性不是很强的服务进行降级处理。 另一方面，在我们日常上线系统的过程中，开发人员为了提高上线的频率，存在很多实时配置的更新，譬如：新老业务代码逻辑的切换，某个配置在某一个时间段的配置更新等。 此外，我们整个网站有很多配置信息是全站或者某一块业务共享的，配置信息需要统一更新维护，譬如：模块业务的redis信息配置，某个数据库集群等等。这样的配置信息往往是写在系统文件里，通过人工的方式一个一个服务器修改，这样一来，缺乏统一的配置和管理功能，不方便维护，以及修改的时候容易出错或者不统一等，配置信息不能更实时的反馈到客户端，不能进行热部署切换等等。另外，在开发维护的过程中，由于各个开发人员开发自己的API实现配置的更新，一方面缺乏系统的高度抽象，在各个业务子系统中散落不一致以及冗余的代码，缺乏一套统一的标准和维护方式。 为此，我们从架构层面解决这些问题，开发一套维护的系统，并制定相应的标准。

主要解决的功能如下：
1.提供客户端的业务监控接入，降低接入成本，与服务端简单集成.(Optional) 2.对服务进行存活监控，提供服务降级和管理功能。 3.统一配置集成，方便开发维护。

主要功能一览图：
https://code.google.com/p/doraemon-config-server/logo?cct=1389170183

实现功能和原理解析
配置信息采用分组管理的功能，每个应用或者业务采用不同的分组进行配置管理。配置信息采用MD5的方式来维持各个信息是否存在更新。

1.支持多种协议的数据传输方式
a.支持TCP协议 b.支持UDP协议 c.支持应用层的HTTP协议数据传输

2.支持应用集群管理功能(TCP和UDP协议支持分组管理集群信息获取功能)
服务端可以实时的获取应用执行的服务器相关的信息。（CPU、JVM使用情况，应用是否可用等等）

3.服务端集群高可用性管理
为了保证服务端的高可用性，系统通过Leader选举的方式产生主服务器，在主服务down掉的情况下，会通过选举的方式产生新的Leader。

4.配置信息Snapshot功能
为了提高服务端和客户端的系统以及数据的安全，以及客户端的可用性，服务端和客户端的数据采用Snapshot文件进行保存，在或者服务端重启的时候还可以不影响正常业务的执行。 并采用增量的信息同步，减少各个系统的数据交互。

5.支持服务降级功能
服务降级功能可以支持多种方式，可以支持智能降级(譬如调用时间超过多久，调用次数超过少的请求进行降级处理)以及非智能降级。 降级方式支持抛异常、返回控制或者模拟返回结果的方式(目前支持javascript和java之间的相互调用，后续可以支持更多的脚本互相调用).

6.与Spring框架集成
为了提高开发效率，简化使用，配置方式与Spring集成，调用也支持Spring的依赖注入。

7.配置信息信息测试功能
8.支持多种存储方式
支持Database，Zookeeper，Redis等存储方式，支持多级缓存 ..............

开发指南
1. 开发配置更新Listener（com.jd.doraemon.client.listener.ConfigurationListener）
接口描述： public interface ConfigurationListener extends EventListener{ public void handleEvent(ConfigurationEvent event); } ConfigurationListener 接口定义了配置更新的时候，客户端接收到配置更新事件的处理方式。

Spring集成配置
<doraemon:application name="demo" groups="demogroup1,demogroup2" />
<doraemon:threadpool type="FIXED" size="5" />
<doraemon:server name="defaultServer" address="tcp://127.0.0.1;tcp://127.0.0.2;http://127.0.0.1:8080" />
<doraemon:listener class="com.jd.doraemon.client.listener.StatusChangeListener"/>
2.com.jd.doraemon.client.listener.ThreadPoolConfigurationListener
接口定义

public interface ThreadPoolConfigurationListener extends ConfigurationListener {
public ThreadPoolExecutor getThreadPool();
}
和ConfigurationListener类似，特点是可以自定义执行的线程池。

3.注解功能支持
@Configuration(name = "configurationBeanId")
public class AnotationConfiguration {
@Property(group = "group1", key = "p1")
static String property1;
@Property(group = "group2")
static String property2;
@Property(group = "group1", key = "p1")
static Long property3;
}
@Configuration标记一个类与配置信息进行绑定，那么属性最终会生成一个bean id是configurationBeanId为名称的Bean，这样在Spring范围内可以灵活的进行依赖注入的使用 还有更多的功能.............
