package dmit2015.service;

import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.mail.MessagingException;


@Singleton		// Instruct the container to create a single instance of this EJB
@Startup		// Create this EJB is created when this app starts
public class AutomaticTimersBean {	// Also known as Calendar-Based Timers

	/**
	 * Assuming you have define the following entries in your web.xml file
	 *     <env-entry>
	 *         <env-entry-name>ca.dmit2015.config.SYSADMIN_EMAIL</env-entry-name>
	 *         <env-entry-type>java.lang.String</env-entry-type>
	 *         <env-entry-value>yourUsername@yourEmailServer</env-entry-value>
	 *     </env-entry>
	 */
	@Resource(name="ca.dmit2015.config.SYSADMIN_EMAIL")
	private String mailToAddress;

	@Inject
	private MailBean mailBean;

	@Schedules({
		@Schedule(second = "0", minute ="50", hour = "15", dayOfWeek = "Tue", month = "Sep-Dec", year = "2020",info ="DMIT2015 Section OA02 Class Notification", persistent = false),
		@Schedule(second = "0", minute ="50", hour = "10", dayOfWeek = "Thu", month = "Sep-Dec", year = "2020",info ="DMIT2015 Section OA02 Class Notification", persistent = false),
		@Schedule(second = "0", minute ="50", hour = "13", dayOfWeek = "Fri", month = "Sep-Dec", year = "2020",info ="DMIT2015 Section OA02 Class Notification", persistent = false)
	})
	public void dmit2015SectionOA02ClassNotifiation(Timer timer) {
		if (!StringUtils.isBlank(mailToAddress)) {
			String mailSubject = timer.getInfo().toString();
			String mailText = "You have an event at " + timer.getSchedule().toString();
			try {
				mailBean.sendMail(mailToAddress, mailSubject, mailText);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}

	@Schedules({
		@Schedule(second = "0", minute ="50", hour = "12", dayOfWeek = "Mon,Wed", month = "Sep-Dec", year = "2020", info ="DMIT2015 Section OA03 Class Notification", persistent = false),
		@Schedule(second = "0", minute ="50", hour = "15", dayOfWeek = "Thu", month = "Sep-Dec", year = "2020", info ="DMIT2015 Section OA03 Class Notification", persistent = false)
	})
	public void dmit2015SectionOA03ClassNotifiation(Timer timer) {
		if (!StringUtils.isBlank(mailToAddress)) {
			String mailSubject = timer.getInfo().toString();
			String mailText = "You have an event at " + timer.getSchedule().toString();
			try {
				mailBean.sendMail(mailToAddress, mailSubject, mailText);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}

	@Schedule(second = "0", minute ="50", hour = "17", dayOfWeek = "Tue, Thu", month = "Sep-Dec", year = "2020", info ="BAIS3150 Section OE01 Class Notification", persistent = false)
	public void bais3150SectionOE01ClassNotifiation(Timer timer) {
		if (!StringUtils.isBlank(mailToAddress)) {
			String mailSubject = timer.getInfo().toString();
			String mailText = "You have an event at " + timer.getSchedule().toString();
			try {
				mailBean.sendMail(mailToAddress, mailSubject, mailText);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}

	@Schedule(minute ="7", hour = "12", dayOfWeek = "Thu", info ="DMIT2015-OA02 Automatic Timer Demo", persistent = false)
	public void dmit2015OA02AutomaticTimerDemo(Timer timer) {
		System.out.println("Hello, World from an EJB automatic timer with info: " + timer.getInfo());
        String mailText = String.format("You have an event %s on %s %s %s, %s  ",
                timer.getInfo().toString(),
                timer.getSchedule().getDayOfWeek(),
                timer.getSchedule().getMonth(),
                timer.getSchedule().getDayOfMonth(),
                timer.getSchedule().getYear()
        );
        System.out.println(mailText);
//		if (!StringUtils.isBlank(mailToAddress)) {
//			String mailSubject = timer.getInfo().toString();
//			String mailText = "You have an event at " + timer.getSchedule().toString();
//			try {
//				mailBean.sendMail(mailToAddress, mailSubject, mailText);
//			} catch (MessagingException e) {
//				e.printStackTrace();
//			}
//		}
	}
}