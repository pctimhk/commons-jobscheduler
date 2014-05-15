//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.01.17 at 03:00:56 PM MEZ 
//


package com.sos.scheduler.model.objects;

import com.sos.JSHelper.Basics.JSToolBox;
import com.sos.scheduler.model.commands.*;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sos.scheduler.model.objects package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory extends JSToolBox {

    private final static QName _CommandsLockRemove_QNAME = new QName("", "lock.remove");
    private final static QName _CommandsCheckFolders_QNAME = new QName("", "check_folders");
    private final static QName _CommandsOrder_QNAME = new QName("", "order");
    private final static QName _CommandsProcessClass_QNAME = new QName("", "process_class");
    private final static QName _CommandsLock_QNAME = new QName("", "lock");
    private final static QName _CommandsProcessClassRemove_QNAME = new QName("", "process_class.remove");
    private final static QName _CommandsRunTime_QNAME = new QName("", "run_time");
    private final static QName _ShowState_QNAME = new QName("", "show_state");
    private final static QName _ScheduleRemove_QNAME = new QName("", "schedule.remove");
    private final static QName _SubsystemShow_QNAME = new QName("", "subsystem.show");
    private final static QName _SchedulerLogLogCategoriesShow_QNAME = new QName("", "scheduler_log.log_categories.show");
    private final static QName _Commands_QNAME = new QName("", "commands");
    private final static QName _ParamsGet_QNAME = new QName("", "params.get");
    private final static QName _AddOrder_QNAME = new QName("", "add_order");
    private final static QName _Schedule_QNAME = new QName("", "schedule");
    private final static QName _S_QNAME = new QName("", "s");
    private final static QName _ShowSchedulers_QNAME = new QName("", "show_schedulers");
    private final static QName _ModifyHotFolder_QNAME = new QName("", "modify_hot_folder");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sos.scheduler.model.objects
     * 
     */
    public ObjectFactory() {
    }

    public ObjectFactory(final String pstrI18NFileName) {
    	super(pstrI18NFileName);
    }

    /**
     * Create an instance of {@link Job }
     * 
     */
    public Job createJob() {
        return new Job();
    }

    /**
     * Create an instance of {@link Params }
     * 
     */
    public Params createParams() {
        return new Params();
    }

    /**
     * Create an instance of {@link JobChain }
     * 
     */
    public JobChain createJobChain() {
        return new JobChain();
    }

    /**
     * Create an instance of {@link Holidays }
     * 
     */
    public Holidays createHolidays() {
        return new Holidays();
    }

    /**
     * Create an instance of {@link ClusterMemberCommand }
     * 
     */
    public ClusterMemberCommand createClusterMemberCommand() {
        return new ClusterMemberCommand();
    }

    /**
     * Create an instance of {@link Security }
     * 
     */
    public Security createSecurity() {
        return new Security();
    }

    /**
     * Create an instance of {@link RegisterRemoteScheduler }
     * 
     */
    public RegisterRemoteScheduler createRegisterRemoteScheduler() {
        return new RegisterRemoteScheduler();
    }

    /**
     * Create an instance of {@link Spooler }
     * 
     */
    public Spooler createSpooler() {
        return new Spooler();
    }

    /**
     * Create an instance of {@link ServiceRequest }
     * 
     */
    public ServiceRequest createServiceRequest() {
        return new ServiceRequest();
    }

    /**
     * Create an instance of {@link Ultimos }
     * 
     */
    public Ultimos createUltimos() {
        return new Ultimos();
    }

    /**
     * Create an instance of {@link Monthdays }
     * 
     */
    public Monthdays createMonthdays() {
        return new Monthdays();
    }

    /**
     * Create an instance of {@link com.sos.scheduler.model.objects.Weekdays }
     * 
     */
    public com.sos.scheduler.model.objects.Weekdays createWeekdays() {
        return new com.sos.scheduler.model.objects.Weekdays();
    }

    /**
     * Create an instance of {@link Spooler.Config }
     * 
     */
    public Spooler.Config createSpoolerConfig() {
        return new Spooler.Config();
    }

    /**
     * Create an instance of {@link Holidays.Weekdays }
     * 
     */
    public Holidays.Weekdays createHolidaysWeekdays() {
        return new Holidays.Weekdays();
    }

    /**
     * Create an instance of {@link HttpAuthentication }
     * 
     */
    public HttpAuthentication createHttpAuthentication() {
        return new HttpAuthentication();
    }

    /**
     * Create an instance of {@link HttpAuthentication.HttpUsers }
     * 
     */
    public HttpAuthentication.HttpUsers createHttpAuthenticationHttpUsers() {
        return new HttpAuthentication.HttpUsers();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link RunTime }
     * 
     */
    public RunTime createRunTime() {
        return new RunTime();
    }

    /**
     * Create an instance of {@link Environment }
     * 
     */
    public Environment createEnvironment() {
        return new Environment();
    }

    /**
     * Create an instance of {@link ScheduleRemove }
     * 
     */
    public ScheduleRemove createScheduleRemove() {
        return new ScheduleRemove();
    }

    /**
     * Create an instance of {@link RemoteSchedulerStartRemoteTask }
     * 
     */
    public RemoteSchedulerStartRemoteTask createRemoteSchedulerStartRemoteTask() {
        return new RemoteSchedulerStartRemoteTask();
    }

    /**
     * Create an instance of {@link KillTask }
     * 
     */
    public KillTask createKillTask() {
        return new KillTask();
    }

    /**
     * Create an instance of {@link Jobs }
     * 
     */
    public Jobs createJobs() {
        return new Jobs();
    }

    /**
     * Create an instance of {@link JobSettings }
     * 
     */
    public JobSettings createJobSettings() {
        return new JobSettings();
    }

    /**
     * Create an instance of {@link Job.Description }
     * 
     */
    public Job.Description createJobDescription() {
        return new Job.Description();
    }

    /**
     * Create an instance of {@link Job.LockUse }
     * 
     */
    public Job.LockUse createJobLockUse() {
        return new Job.LockUse();
    }

    /**
     * Create an instance of {@link Param }
     * 
     */
    public Param createParam() {
        return new Param();
    }

    /**
     * Create an instance of {@link Params.CopyParams }
     * 
     */
    public Params.CopyParams createParamsCopyParams() {
        return new Params.CopyParams();
    }

    /**
     * Create an instance of {@link Params.Include }
     * 
     */
    public Params.Include createParamsInclude() {
        return new Params.Include();
    }

    /**
     * Create an instance of {@link Script }
     * 
     */
    public Script createScript() {
        return new Script();
    }

    /**
     * Create an instance of {@link com.sos.scheduler.model.objects.Include }
     * 
     */
    public com.sos.scheduler.model.objects.Include createInclude() {
        return new com.sos.scheduler.model.objects.Include();
    }

    /**
     * Create an instance of {@link Job.Process }
     * 
     */
    public Job.Process createJobProcess() {
        return new Job.Process();
    }

    /**
     * Create an instance of {@link Job.Monitor }
     * 
     */
    public Job.Monitor createJobMonitor() {
        return new Job.Monitor();
    }

    /**
     * Create an instance of {@link Job.StartWhenDirectoryChanged }
     * 
     */
    public Job.StartWhenDirectoryChanged createJobStartWhenDirectoryChanged() {
        return new Job.StartWhenDirectoryChanged();
    }

    /**
     * Create an instance of {@link Job.DelayAfterError }
     * 
     */
    public Job.DelayAfterError createJobDelayAfterError() {
        return new Job.DelayAfterError();
    }

    /**
     * Create an instance of {@link Job.DelayOrderAfterSetback }
     * 
     */
    public Job.DelayOrderAfterSetback createJobDelayOrderAfterSetback() {
        return new Job.DelayOrderAfterSetback();
    }

    /**
     * Create an instance of {@link Commands }
     * 
     */
    public Commands createCommands() {
        return new Commands();
    }

    /**
     * Create an instance of {@link SubsystemShow }
     * 
     */
    public SubsystemShow createSubsystemShow() {
        return new SubsystemShow();
    }

    /**
     * Create an instance of {@link StartJob }
     * 
     */
    public StartJob createStartJob() {
        return new StartJob();
    }

    /**
     * Create an instance of {@link ModifyJob }
     * 
     */
    public ModifyJob createModifyJob() {
        return new ModifyJob();
    }

    /**
     * Create an instance of {@link ShowJobChains }
     * 
     */
    public ShowJobChains createShowJobChains() {
        return new ShowJobChains();
    }

    /**
     * Create an instance of {@link JobChainModify }
     * 
     */
    public JobChainModify createJobChainModify() {
        return new JobChainModify();
    }

    /**
     * Create an instance of {@link RemoveJobChain }
     * 
     */
    public RemoveJobChain createRemoveJobChain() {
        return new RemoveJobChain();
    }

    /**
     * Create an instance of {@link ProcessClassRemove }
     * 
     */
    public ProcessClassRemove createProcessClassRemove() {
        return new ProcessClassRemove();
    }

    /**
     * Create an instance of {@link ShowCalendar }
     * 
     */
    public ShowCalendar createShowCalendar() {
        return new ShowCalendar();
    }

    /**
     * Create an instance of {@link CheckFolders }
     * 
     */
    public CheckFolders createCheckFolders() {
        return new CheckFolders();
    }

    /**
     * Create an instance of {@link JobChains }
     * 
     */
    public JobChains createJobChains() {
        return new JobChains();
    }

    /**
     * Create an instance of {@link JobChainNodeJobChain }
     * 
     */
    public JobChainNodeJobChain createJobChainNodeJobChain() {
        return new JobChainNodeJobChain();
    }

    /**
     * Create an instance of {@link JobChainNodeEnd }
     * 
     */
    public JobChainNodeEnd createJobChainNodeEnd() {
        return new JobChainNodeEnd();
    }

    /**
     * Create an instance of {@link JobChain.FileOrderSource }
     * 
     */
    public JobChain.FileOrderSource createJobChainFileOrderSource() {
        return new JobChain.FileOrderSource();
    }

    /**
     * Create an instance of {@link JobChain.JobChainNode }
     * 
     */
    public JobChain.JobChainNode createJobChainJobChainNode() {
        return new JobChain.JobChainNode();
    }

    /**
     * Create an instance of {@link JobChain.FileOrderSink }
     * 
     */
    public JobChain.FileOrderSink createJobChainFileOrderSink() {
        return new JobChain.FileOrderSink();
    }

    /**
     * Create an instance of {@link ModifySpooler }
     * 
     */
    public ModifySpooler createModifySpooler() {
        return new ModifySpooler();
    }

    /**
     * Create an instance of {@link Period }
     * 
     */
    public Period createPeriod() {
        return new Period();
    }

    /**
     * Create an instance of {@link EventsGet }
     * 
     */
    public EventsGet createEventsGet() {
        return new EventsGet();
    }

    /**
     * Create an instance of {@link ShowJobChain }
     * 
     */
    public ShowJobChain createShowJobChain() {
        return new ShowJobChain();
    }

    /**
     * Create an instance of {@link HttpServer }
     * 
     */
    public HttpServer createHttpServer() {
        return new HttpServer();
    }

    /**
     * Create an instance of {@link WebService }
     * 
     */
    public WebService createWebService() {
        return new WebService();
    }

    /**
     * Create an instance of {@link HttpDirectory }
     * 
     */
    public HttpDirectory createHttpDirectory() {
        return new HttpDirectory();
    }

    /**
     * Create an instance of {@link Holiday }
     * 
     */
    public Holiday createHoliday() {
        return new Holiday();
    }

    /**
     * Create an instance of {@link RemoveOrder }
     * 
     */
    public RemoveOrder createRemoveOrder() {
        return new RemoveOrder();
    }

    /**
     * Create an instance of {@link ShowTask }
     * 
     */
    public ShowTask createShowTask() {
        return new ShowTask();
    }

    /**
     * Create an instance of {@link ProcessClass }
     * 
     */
    public ProcessClass createProcessClass() {
        return new ProcessClass();
    }

    /**
     * Create an instance of {@link ShowState }
     * 
     */
    public ShowState createShowState() {
        return new ShowState();
    }

    /**
     * Create an instance of {@link ProcessClasses }
     * 
     */
    public ProcessClasses createProcessClasses() {
        return new ProcessClasses();
    }

    /**
     * Create an instance of {@link SchedulerLogLogCategoriesReset }
     * 
     */
    public SchedulerLogLogCategoriesReset createSchedulerLogLogCategoriesReset() {
        return new SchedulerLogLogCategoriesReset();
    }

    /**
     * Create an instance of {@link com.sos.scheduler.model.commands.Terminate }
     * 
     */
    public com.sos.scheduler.model.commands.Terminate createTerminate() {
        return new com.sos.scheduler.model.commands.Terminate();
    }

    /**
     * Create an instance of {@link ShowJobs }
     * 
     */
    public ShowJobs createShowJobs() {
        return new ShowJobs();
    }

    /**
     * Create an instance of {@link ShowOrder }
     * 
     */
    public ShowOrder createShowOrder() {
        return new ShowOrder();
    }

    /**
     * Create an instance of {@link ClusterMemberCommand.Terminate }
     * 
     */
    public ClusterMemberCommand.Terminate createClusterMemberCommandTerminate() {
        return new ClusterMemberCommand.Terminate();
    }

    /**
     * Create an instance of {@link Security.AllowedHost }
     * 
     */
    public Security.AllowedHost createSecurityAllowedHost() {
        return new Security.AllowedHost();
    }

    /**
     * Create an instance of {@link RemoteSchedulerRemoteTaskClose }
     * 
     */
    public RemoteSchedulerRemoteTaskClose createRemoteSchedulerRemoteTaskClose() {
        return new RemoteSchedulerRemoteTaskClose();
    }

    /**
     * Create an instance of {@link LockRemove }
     * 
     */
    public LockRemove createLockRemove() {
        return new LockRemove();
    }

    /**
     * Create an instance of {@link SupervisorRemoteSchedulerConfigurationFetchUpdatedFiles }
     * 
     */
    public SupervisorRemoteSchedulerConfigurationFetchUpdatedFiles createSupervisorRemoteSchedulerConfigurationFetchUpdatedFiles() {
        return new SupervisorRemoteSchedulerConfigurationFetchUpdatedFiles();
    }

    /**
     * Create an instance of {@link ConfigurationFile }
     * 
     */
    public ConfigurationFile createConfigurationFile() {
        return new ConfigurationFile();
    }

    /**
     * Create an instance of {@link ConfigurationDirectory }
     * 
     */
    public ConfigurationDirectory createConfigurationDirectory() {
        return new ConfigurationDirectory();
    }

    /**
     * Create an instance of {@link WebServices }
     * 
     */
    public WebServices createWebServices() {
        return new WebServices();
    }

    /**
     * Create an instance of {@link JobChainNodeModify }
     * 
     */
    public JobChainNodeModify createJobChainNodeModify() {
        return new JobChainNodeModify();
    }

    /**
     * Create an instance of {@link ModifyOrder }
     * 
     */
    public ModifyOrder createModifyOrder() {
        return new ModifyOrder();
    }

    /**
     * Create an instance of {@link XmlPayload }
     *
     */
    public XmlPayload createXmlPayload() {
        XmlPayload p = new XmlPayload();
        return p;
    }

    /**
     * Create an instance of {@link ParamGet }
     * 
     */
    public ParamGet createParamGet() {
        return new ParamGet();
    }

    /**
     * Create an instance of {@link ModifyHotFolder }
     * 
     */
    public ModifyHotFolder createModifyHotFolder() {
        return new ModifyHotFolder();
    }

    /**
     * Create an instance of {@link RegisterRemoteScheduler.ERROR }
     * 
     */
    public RegisterRemoteScheduler.ERROR createRegisterRemoteSchedulerERROR() {
        return new RegisterRemoteScheduler.ERROR();
    }

    /**
     * Create an instance of {@link Note }
     * 
     */
    public Note createNote() {
        return new Note();
    }

    /**
     * Create an instance of {@link ShowHistory }
     * 
     */
    public ShowHistory createShowHistory() {
        return new ShowHistory();
    }

    /**
     * Create an instance of {@link Lock }
     * 
     */
    public Lock createLock() {
        return new Lock();
    }

    /**
     * Create an instance of {@link AddJobs }
     * 
     */
    public AddJobs createAddJobs() {
        return new AddJobs();
    }

    /**
     * Create an instance of {@link SchedulerLogLogCategoriesSet }
     * 
     */
    public SchedulerLogLogCategoriesSet createSchedulerLogLogCategoriesSet() {
        return new SchedulerLogLogCategoriesSet();
    }

    /**
     * Create an instance of {@link Cluster }
     * 
     */
    public Cluster createCluster() {
        return new Cluster();
    }

    /**
     * Create an instance of {@link Spooler.Answer }
     * 
     */
    public Spooler.Answer createSpoolerAnswer() {
        return new Spooler.Answer();
    }

    /**
     * Create an instance of {@link ShowJob }
     * 
     */
    public ShowJob createShowJob() {
        return new ShowJob();
    }

    /**
     * Create an instance of {@link ServiceRequest.Content }
     * 
     */
    public ServiceRequest.Content createServiceRequestContent() {
        return new ServiceRequest.Content();
    }

    /**
     * Create an instance of {@link LicenceUse }
     * 
     */
    public LicenceUse createLicenceUse() {
        return new LicenceUse();
    }

    /**
     * Create an instance of {@link SchedulerScript }
     * 
     */
    public SchedulerScript createSchedulerScript() {
        return new SchedulerScript();
    }

    /**
     * Create an instance of {@link Ultimos.Day }
     * 
     */
    public Ultimos.Day createUltimosDay() {
        return new Ultimos.Day();
    }

    /**
     * Create an instance of {@link Monthdays.Day }
     * 
     */
    public Monthdays.Day createMonthdaysDay() {
        return new Monthdays.Day();
    }

    /**
     * Create an instance of {@link Monthdays.Weekday }
     * 
     */
    public Monthdays.Weekday createMonthdaysWeekday() {
        return new Monthdays.Weekday();
    }

    /**
     * Create an instance of {@link com.sos.scheduler.model.objects.Weekdays.Day }
     * 
     */
    public com.sos.scheduler.model.objects.Weekdays.Day createWeekdaysDay() {
        return new com.sos.scheduler.model.objects.Weekdays.Day();
    }

    /**
     * Create an instance of {@link Spooler.Config.Base }
     * 
     */
    public Spooler.Config.Base createSpoolerConfigBase() {
        return new Spooler.Config.Base();
    }

    /**
     * Create an instance of {@link Spooler.Config.Schedules }
     * 
     */
    public Spooler.Config.Schedules createSpoolerConfigSchedules() {
        return new Spooler.Config.Schedules();
    }

    /**
     * Create an instance of {@link Spooler.Config.Locks }
     * 
     */
    public Spooler.Config.Locks createSpoolerConfigLocks() {
        return new Spooler.Config.Locks();
    }

    /**
     * Create an instance of {@link Holidays.Weekdays.Day }
     * 
     */
    public Holidays.Weekdays.Day createHolidaysWeekdaysDay() {
        return new Holidays.Weekdays.Day();
    }

    /**
     * Create an instance of {@link HttpAuthentication.HttpUsers.HttpUser }
     * 
     */
    public HttpAuthentication.HttpUsers.HttpUser createHttpAuthenticationHttpUsersHttpUser() {
        return new HttpAuthentication.HttpUsers.HttpUser();
    }

    /**
     * Create an instance of {@link Order.Payload }
     * 
     */
    public Order.Payload createOrderPayload() {
        return new Order.Payload();
    }

    /**
     * Create an instance of {@link RunTime.At }
     * 
     */
    public RunTime.At createRunTimeAt() {
        return new RunTime.At();
    }

    /**
     * Create an instance of {@link RunTime.Date }
     * 
     */
    public RunTime.Date createRunTimeDate() {
        return new RunTime.Date();
    }

    /**
     * Create an instance of {@link RunTime.Month }
     * 
     */
    public RunTime.Month createRunTimeMonth() {
        return new RunTime.Month();
    }

    /**
     * Create an instance of {@link Environment.Variable }
     * 
     */
    public Environment.Variable createEnvironmentVariable() {
        return new Environment.Variable();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LockRemove }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "lock.remove", scope = Commands.class)
    public JAXBElement<LockRemove> createCommandsLockRemove(LockRemove value) {
        return new JAXBElement<LockRemove>(_CommandsLockRemove_QNAME, LockRemove.class, Commands.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckFolders }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "check_folders", scope = Commands.class)
    public JAXBElement<CheckFolders> createCommandsCheckFolders(CheckFolders value) {
        return new JAXBElement<CheckFolders>(_CommandsCheckFolders_QNAME, CheckFolders.class, Commands.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Order }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "order", scope = Commands.class)
    public JAXBElement<Order> createCommandsOrder(Order value) {
        return new JAXBElement<Order>(_CommandsOrder_QNAME, Order.class, Commands.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessClass }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "process_class", scope = Commands.class)
    public JAXBElement<ProcessClass> createCommandsProcessClass(ProcessClass value) {
        return new JAXBElement<ProcessClass>(_CommandsProcessClass_QNAME, ProcessClass.class, Commands.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Lock }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "lock", scope = Commands.class)
    public JAXBElement<Lock> createCommandsLock(Lock value) {
        return new JAXBElement<Lock>(_CommandsLock_QNAME, Lock.class, Commands.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessClassRemove }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "process_class.remove", scope = Commands.class)
    public JAXBElement<ProcessClassRemove> createCommandsProcessClassRemove(ProcessClassRemove value) {
        return new JAXBElement<ProcessClassRemove>(_CommandsProcessClassRemove_QNAME, ProcessClassRemove.class, Commands.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RunTime }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "run_time", scope = Commands.class)
    public JAXBElement<RunTime> createCommandsRunTime(RunTime value) {
        return new JAXBElement<RunTime>(_CommandsRunTime_QNAME, RunTime.class, Commands.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowState }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "show_state")
    public JAXBElement<ShowState> createShowState(ShowState value) {
        return new JAXBElement<ShowState>(_ShowState_QNAME, ShowState.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ScheduleRemove }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "schedule.remove")
    public JAXBElement<ScheduleRemove> createScheduleRemove(ScheduleRemove value) {
        return new JAXBElement<ScheduleRemove>(_ScheduleRemove_QNAME, ScheduleRemove.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckFolders }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "check_folders")
    public JAXBElement<CheckFolders> createCheckFolders(CheckFolders value) {
        return new JAXBElement<CheckFolders>(_CommandsCheckFolders_QNAME, CheckFolders.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubsystemShow }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "subsystem.show")
    public JAXBElement<SubsystemShow> createSubsystemShow(SubsystemShow value) {
        return new JAXBElement<SubsystemShow>(_SubsystemShow_QNAME, SubsystemShow.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "scheduler_log.log_categories.show")
    public JAXBElement<Object> createSchedulerLogLogCategoriesShow(Object value) {
        return new JAXBElement<Object>(_SchedulerLogLogCategoriesShow_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Commands }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "commands")
    public JAXBElement<Commands> createCommands(Commands value) {
        return new JAXBElement<Commands>(_Commands_QNAME, Commands.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Lock }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "lock")
    public JAXBElement<Lock> createLock(Lock value) {
        return new JAXBElement<Lock>(_CommandsLock_QNAME, Lock.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "params.get")
    public JAXBElement<Object> createParamsGet(Object value) {
        return new JAXBElement<Object>(_ParamsGet_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Order }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "add_order")
    public JAXBElement<Order> createAddOrder(Order value) {
        return new JAXBElement<Order>(_AddOrder_QNAME, Order.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LockRemove }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "lock.remove")
    public JAXBElement<LockRemove> createLockRemove(LockRemove value) {
        return new JAXBElement<LockRemove>(_CommandsLockRemove_QNAME, LockRemove.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RunTime }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "schedule")
    public JAXBElement<RunTime> createSchedule(RunTime value) {
        return new JAXBElement<RunTime>(_Schedule_QNAME, RunTime.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowState }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "s")
    public JAXBElement<ShowState> createS(ShowState value) {
        return new JAXBElement<ShowState>(_S_QNAME, ShowState.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Order }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "order")
    public JAXBElement<Order> createOrder(Order value) {
        return new JAXBElement<Order>(_CommandsOrder_QNAME, Order.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessClass }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "process_class")
    public JAXBElement<ProcessClass> createProcessClass(ProcessClass value) {
        return new JAXBElement<ProcessClass>(_CommandsProcessClass_QNAME, ProcessClass.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowState }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "show_schedulers")
    public JAXBElement<ShowState> createShowSchedulers(ShowState value) {
        return new JAXBElement<ShowState>(_ShowSchedulers_QNAME, ShowState.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyHotFolder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "modify_hot_folder")
    public JAXBElement<ModifyHotFolder> createModifyHotFolder(ModifyHotFolder value) {
        return new JAXBElement<ModifyHotFolder>(_ModifyHotFolder_QNAME, ModifyHotFolder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessClassRemove }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "process_class.remove")
    public JAXBElement<ProcessClassRemove> createProcessClassRemove(ProcessClassRemove value) {
        return new JAXBElement<ProcessClassRemove>(_CommandsProcessClassRemove_QNAME, ProcessClassRemove.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RunTime }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "run_time")
    public JAXBElement<RunTime> createRunTime(RunTime value) {
        return new JAXBElement<RunTime>(_CommandsRunTime_QNAME, RunTime.class, null, value);
    }

}
