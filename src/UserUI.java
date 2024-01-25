
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserUI {
    private DataBase db;
    private User currentUser;
    private JFrame us;
    public UserUI(DataBase database) {
        this.db = database;
        initializeLoginWindow();
    }
    void initializeLoginWindow() {
        us = new JFrame("UniversitySystem"); 
        JButton lb = new JButton("enter");
        JTextField ltf = new JTextField("login");
        JPasswordField ptf = new JPasswordField("password");
        JLabel t;
        JLabel t1;
        t = new JLabel("login");
        t1 = new JLabel("password");
        t.setBounds(150, 65, 90, 25);
        t1.setBounds(150, 120, 90, 25);
        ltf.setBounds(150, 90, 200, 25);
        ptf.setBounds(150, 145, 200, 25);
        lb.setBounds(200, 195, 85, 25);
        us.add(t);
        us.add(t1);
        us.add(lb);
        us.add(ltf);
        us.add(ptf);
        us.setSize(600, 600);
        us.setLayout(null);
        us.setVisible(true);
        lb.addActionListener(e -> {
            String userID = ltf.getText();
            String password = new String(ptf.getPassword());
            boolean loggedIn = db.login(userID, password);
            if (loggedIn) {
                currentUser = db.getUser(userID);
                openMainWindow();
                us.dispose();
            } else {
                currentUser = null;
                JOptionPane.showMessageDialog(null, "Login failed");
            }
        });
    }
    private void openMainWindow() {
        JFrame mainWindow = new JFrame("Main Window");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = mainPanel();
        
        JButton save = new JButton("save");
        save.addActionListener(e -> {
    		try {
    			DataBase.write();
    		} catch (IOException ex) {
    			// TODO Auto-generated catch block
    			ex.printStackTrace();
    		}
        });
        panel.add(save);
          		
        JButton nws = new JButton("news"); 
        nws.addActionListener(e -> {
        	System.out.println(currentUser.viewNews());
        });
        panel.add(nws);
        
        if(currentUser instanceof Employee) {
        	panel.add(employeePanel());
        }
        if((currentUser instanceof CanBeResearcher) && (((CanBeResearcher) currentUser).getResearcher()!=null)) {
        	panel.add(researcherInterface());
        }
        else if((currentUser instanceof CanBeResearcher) && (((CanBeResearcher) currentUser).getResearcher()==null)) {
        	JButton becomeRButton = new JButton("become Researcher"); 
        	becomeRButton.addActionListener(e -> {
        		((CanBeResearcher) currentUser).becomeResearcher();
        	});
        	panel.add(becomeRButton);
        }
        if (panel != null) {
            JButton changePasswordButton = new JButton("Change Password");
            changePasswordButton.addActionListener(e -> {
                String newPassword = JOptionPane.showInputDialog(mainWindow, "Enter new password:");
                if (newPassword != null && !newPassword.isEmpty()) {
                    currentUser.changePassword(newPassword);
                    JOptionPane.showMessageDialog(mainWindow, "Password changed successfully!");
                } else {
                    JOptionPane.showMessageDialog(mainWindow, "Password empty!");
                }
            });

            JButton logoutButton = new JButton("Logout");
            logoutButton.addActionListener(e -> {
                logout();
                us.setVisible(true);
                mainWindow.dispose();
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(changePasswordButton);
            buttonPanel.add(logoutButton);
             	
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(panel, BorderLayout.CENTER);
            mainPanel.add(buttonPanel, BorderLayout.PAGE_END);
            mainWindow.add(mainPanel);
            mainWindow.setSize(600, 400);
            mainWindow.setVisible(true);
            if (currentUser.getUserType() != UserType.TechSupportSpecialist) {
	            JButton getHelpButton = new JButton("Get Help");
	            getHelpButton.addActionListener(e -> {
	                String orderDescription = JOptionPane.showInputDialog("Enter Order Description:");
	                if (orderDescription != null && !orderDescription.isEmpty()) {
	                    if (currentUser != null && currentUser.getTeachSupportSp() != null) {
	                        UrgencyLevel[] urgencyLevels = UrgencyLevel.values();
	                        UrgencyLevel urgencyLevel = (UrgencyLevel) JOptionPane.showInputDialog(
	                            null,
	                            "Select Urgency Level:",
	                            "Urgency Level",
	                            JOptionPane.PLAIN_MESSAGE,
	                            null,
	                            urgencyLevels,
	                            urgencyLevels[0]
	                        );
	
	                        if (urgencyLevel != null) {
	                            currentUser.getSupport(orderDescription, urgencyLevel);
	                            System.out.println("Order Description: " + orderDescription);
	                            System.out.println("Urgency Level: " + urgencyLevel);
	                        } else {
	                            System.out.println("There is no urgency level selected!");
	                        }
	                    } else {
	                        System.out.println("Specialist not available!");
	                    }
	                } else {
	                    System.out.println("There is no order description!");
	                }
	            });
	
	            buttonPanel.add(getHelpButton);
            }
        }
    }
    private JPanel employeePanel() {
    	JPanel mainPanel = new JPanel();

    	JButton msg = new JButton("send message");
    	msg.addActionListener(e -> {
    		String message = JOptionPane.showInputDialog("Input message: ");
    		String eID = JOptionPane.showInputDialog("Input ID of Employee to send: ");
    		Employee em = (Employee) db.getUser(eID);
        	((Employee)currentUser).sendMessage(message, em);
    	});
    	mainPanel.add(msg);
    	
    	JButton viewMsg = new JButton("view message");
    	viewMsg.addActionListener(e -> {
    		System.out.println(((Employee)currentUser).viewMessage());
    	});
    	mainPanel.add(viewMsg);
    	
    	JButton request = new JButton("request to dean");
    	request.addActionListener(e -> {
    		String requestDescription = JOptionPane.showInputDialog("Input description: ");
    		UrgencyLevel[] urgencyLevels = UrgencyLevel.values();
            UrgencyLevel urgencyLevel = (UrgencyLevel) JOptionPane.showInputDialog(
                null,
                "Select Urgency Level:",
                "Urgency Level",
                JOptionPane.PLAIN_MESSAGE,
                null,
                urgencyLevels,
                urgencyLevels[0]
            );
            ((Employee)currentUser).requestToDean(requestDescription,urgencyLevel);
    	});
    	mainPanel.add(request);
    	
		return mainPanel;
    }
    private JPanel createDeanPanel() {
        JPanel mainPanel = new JPanel();
        JLabel label = new JLabel("Welcome, Dean!");
        mainPanel.add(label);

        JButton viewRequestsButton = new JButton("View All Requests");
        viewRequestsButton.addActionListener(e -> {
            List<Request> requests = ((Dean) currentUser).getRequests();
            if (requests == null || requests.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No requests available.");
            } else {
                Object[] options = requests.toArray(); 
                Request selectedRequest = (Request) JOptionPane.showInputDialog(
                    null,
                    "Select a request:",
                    "Requests",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
                );

                if (selectedRequest != null) {
                    handleRequest(selectedRequest);
                }
            }
        });
        mainPanel.add(viewRequestsButton);
 
        JButton viewComplaintsButton = new JButton("View All Complaints");
        viewComplaintsButton.addActionListener(e -> {
            List<Complain> complaints = ((Dean) currentUser).getComplaints();
            if (complaints == null || complaints.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No complaints available.");
            } else {
                Object[] options = complaints.toArray(); 
                Complain selectedComplaint = (Complain) JOptionPane.showInputDialog(
                    null,
                    "Select a complaint:",
                    "Complaints",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
                );

                if (selectedComplaint != null) {
                    handleComplaint(selectedComplaint);
                }
            }
        });
        mainPanel.add(viewComplaintsButton);

        return mainPanel;
    }

    private void handleRequest(Request request) {
        int choice = JOptionPane.showOptionDialog(
            null,
            "Do you want to accept or reject this request?",
            "Request Action",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            new String[]{"Accept", "Reject"},
            "Accept"
        );

        if (choice == JOptionPane.YES_OPTION) {
            ((Dean) currentUser).signRequest(request);
        } else if (choice == JOptionPane.NO_OPTION) {
            ((Dean) currentUser).rejectRequest(request);
        }
    }

    private void handleComplaint(Complain complaint) {
        int choice = JOptionPane.showOptionDialog(
            null,
            "Do you want to accept or reject this complaint?",
            "Complaint Action",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            new String[]{"Accept", "Reject"},
            "Accept"
        );

        if (choice == JOptionPane.YES_OPTION) {
            ((Dean) currentUser).acceptComplaint(complaint);
        } else if (choice == JOptionPane.NO_OPTION) {
            ((Dean) currentUser).rejectComplaint(complaint);
        }
    }

    private JPanel createAdminPanel() {
        JPanel mainPanel = new JPanel();
        JLabel label = new JLabel("Welcome, Admin!");
        mainPanel.add(label);
        JTextField userIDField = new JTextField(15);
        JButton viewLogFilesButton = new JButton("View User Action Log Files");
        viewLogFilesButton.addActionListener(e -> {
            String userID = userIDField.getText();
            if (userID != null && !userID.isEmpty()) {
            	System.out.println("Actions : ");
            	((Admin) currentUser).viewLogFilesAboutUserActions(userID);
            } else {
                System.out.println("There is no User ID provided!");
            }	
        });
        mainPanel.add(viewLogFilesButton);

        JButton deleteDataB =  new JButton("deleteData");
        deleteDataB.addActionListener(e -> {
            db.deleteData();
        });
        mainPanel.add(deleteDataB);
        
        JButton removeUserButton = new JButton("Remove User");
        removeUserButton.addActionListener(e -> {
            String userID = userIDField.getText();
            if (userID != null && !userID.isEmpty()) {
            	((Admin) currentUser).removeUser(userID);
                System.out.println("User removed: " + userID);
            } else {
                System.out.println("There is no User ID provided!");
            }
        });
        mainPanel.add(removeUserButton);

        JButton updateUserButton = new JButton("Update User");
        updateUserButton.addActionListener(e -> {
        	String userID = userIDField.getText();
            if (userID != null && !userID.isEmpty()) {
            	((Admin) currentUser).updateUser(userID);
                System.out.println("User updated: " + userID);
            } else {
                System.out.println("There is no User ID provided!");
            }
        });
        mainPanel.add(updateUserButton);

        JButton viewToRemoveButton = new JButton("View Users to Remove");
        viewToRemoveButton.addActionListener(e -> {
        	((Admin) currentUser).viewToRemove();
        });
        mainPanel.add(viewToRemoveButton);

        mainPanel.add(new JLabel("Enter User ID:"));
        mainPanel.add(userIDField);

        return mainPanel;
    }

    private JPanel createManagerPanel() {
        JPanel mainPanel = new JPanel();
        JLabel label = new JLabel("Welcome, Manager!");
        mainPanel.add(label);

        JButton viewTeachersCoursesButton = new JButton("View Teachers and Courses");
        viewTeachersCoursesButton.addActionListener(e -> {
            String teachersAndCoursesInfo = ((Manager) currentUser).viewTeachersAndCourses();
            System.out.println(teachersAndCoursesInfo);
        });
        mainPanel.add(viewTeachersCoursesButton);

        JButton viewTeachersToRegisterButton = new JButton("viewTeachersToRegister");
        viewTeachersToRegisterButton.addActionListener(e -> {
        	((Manager) currentUser).viewTeacherQueue();
        });
        mainPanel.add(viewTeachersToRegisterButton);
        
        JButton viewStudentsToRegisterButton = new JButton("viewStudentsToRegister");
        viewStudentsToRegisterButton.addActionListener(e -> {
        	((Manager) currentUser).viewStudentQueue();
        });
        mainPanel.add(viewStudentsToRegisterButton);
        		
        JButton assignCourseTeacherButton = new JButton("assignCourseTeacher");
        assignCourseTeacherButton.addActionListener(e -> {
        	String ID = JOptionPane.showInputDialog("Enter Teachers ID ");
        	((Manager) currentUser).assignCourseTeacher(ID);
        });
        mainPanel.add(assignCourseTeacherButton);
        		
        JButton approveStudentRegistrationButton = new JButton("approveStudentRegistration");
        approveStudentRegistrationButton.addActionListener(e -> {
        	String ID = JOptionPane.showInputDialog("Enter Students ID ");
        	((Manager) currentUser).approveStudentRegistration(ID);
        });
        mainPanel.add(approveStudentRegistrationButton);
        		
        JButton viewInfoAboutTeachersStudentButton = new JButton("viewInfoAboutTeachersStudent");
        viewInfoAboutTeachersStudentButton.addActionListener(e -> {
        	String IDofTeacher = JOptionPane.showInputDialog("Enter Teachers ID :");
        	String IDofStudent = JOptionPane.showInputDialog("Enter Student ID :");
        	((Manager) currentUser).viewInfoAboutTeachersStudent(IDofTeacher,IDofStudent);
        });
        mainPanel.add(viewInfoAboutTeachersStudentButton);
        
        JButton assignLessonToTeacherButton = new JButton("assignLessonToTeacher");
        assignLessonToTeacherButton.addActionListener(e -> {
        	String IDofTeacher = JOptionPane.showInputDialog("Enter Teacher ID :");
        	String subject = JOptionPane.showInputDialog("Enter Subject :");
        	String time = JOptionPane.showInputDialog("Enter Time :");		
        	int room = Integer.parseInt(JOptionPane.showInputDialog("Enter Room :"));
        	LessonType[] lessonTypes =  LessonType.values();
        	LessonType lessonType = (LessonType) JOptionPane.showInputDialog(
                    null,
                    "Select Lesson Type:",
                    "Lesson type",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    lessonTypes,
                    lessonTypes[0]
                );
        	((Manager) currentUser).assignLessonToTeacher(subject, lessonType, time, room, IDofTeacher);
        });
        mainPanel.add(assignLessonToTeacherButton);
        		
        JButton removeTeacherLessonButton = new JButton("removeTeacherLesson");//(Lesson lesson, String IDofTeacher)
        removeTeacherLessonButton.addActionListener(e -> {
        	String IDofTeacher = JOptionPane.showInputDialog("Enter Teacher ID :");
        	Teacher teacher = db.getTeacher(IDofTeacher);
        	if(teacher != null) {
	        	Lesson[] lessons = (Lesson[]) teacher.getLessons().toArray();
	        	if(lessons.length <= 0) {
		        	Lesson lesson = (Lesson) JOptionPane.showInputDialog(
							null,
		                    "Select Elective :",
		                    "Elective",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    lessons,
		                    lessons[0]
		                );
		        	((Manager) currentUser).removeTeacherLesson(lesson, IDofTeacher);
	        	}
	        	else {
	        		System.out.println("This Teacher doesnt have lessons");
	        	}
        	}
        	else {
        		System.out.println("There is no such Teacher");
        	}
        });
        mainPanel.add(removeTeacherLessonButton);
        
        JButton addCourseForRegistrationButton = new JButton("Add Course for Registration");//
        addCourseForRegistrationButton.addActionListener(e -> {
        	String courseName = JOptionPane.showInputDialog("Enter Course Name :");
			int lectureNum = Integer.parseInt(JOptionPane.showInputDialog("Enter lecture number :"));
			int practiceNum = Integer.parseInt(JOptionPane.showInputDialog("Enter practice number :"));
			String subject = JOptionPane.showInputDialog("Enter subject :");
			String description = JOptionPane.showInputDialog("Enter description :");
			Elective[] electives = Elective.values();
			Elective elective = (Elective) JOptionPane.showInputDialog(
					null,
                    "Select Elective :",
                    "Elective",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    electives,
                    electives[0]
                );
			((Manager) currentUser).addCourseForRegistration(courseName, lectureNum, practiceNum, subject, description, elective);
        });
        mainPanel.add(addCourseForRegistrationButton);

        JButton reportButton = new JButton("Report");
        reportButton.addActionListener(e -> {
        	String subject = JOptionPane.showInputDialog("Enter Subject :");
        	String semester = JOptionPane.showInputDialog("Enter Semester :");
        	((Manager) currentUser).report(Integer.parseInt(semester),subject);
        });
        mainPanel.add(reportButton);

        JButton addNewsButton = new JButton("add News");
        addNewsButton.addActionListener(e -> {
        	String topic = JOptionPane.showInputDialog("Enter Topic :");
        	String description = JOptionPane.showInputDialog("Enter Description :");
        	((Manager) currentUser).addNews(topic,description);
        });
        mainPanel.add(addNewsButton);
        
        JButton addLessonsToStudentsButton = new JButton("addLessonsToStudents");
        addLessonsToStudentsButton.addActionListener(e -> {
        	((Manager) currentUser).addLessonsToStudents();
        });
        mainPanel.add(addLessonsToStudentsButton);
        
        return mainPanel;
    }

    private JPanel createTeacherPanel() {
        JPanel mainPanel = new JPanel();
        JLabel label = new JLabel("Welcome, Teacher!");
        mainPanel.add(label);
        
        JButton viewRateButton = new JButton("viewRate");
        viewRateButton.addActionListener(e -> {
        	System.out.println(" Rate " + ((Teacher)currentUser).getRate());
        });
        mainPanel.add(viewRateButton);
        
        JButton viewCoursesButton = new JButton("viewCourses");
        viewCoursesButton.addActionListener(e -> {
        	System.out.println(((Teacher)currentUser).viewCourses());
        });
        mainPanel.add(viewCoursesButton);
        
        JButton viewInfoAboutStudentButton = new JButton("viewInfoAboutStudent");
        viewInfoAboutStudentButton.addActionListener(e -> {
        	String ID = JOptionPane.showInputDialog("Enter Student ID :");
        	System.out.print(((Teacher)currentUser).viewInfoAboutStudent(ID));
        });
        mainPanel.add(viewInfoAboutStudentButton);
        
        JButton manageCourseButton = new JButton("manageCourse");
        manageCourseButton.addActionListener(e -> {
        	String courseName = JOptionPane.showInputDialog("Enter courseName :");
        	((Teacher)currentUser).manageCourse(courseName);
        });
        mainPanel.add(manageCourseButton);
        
        JButton putMarkButton = new JButton("put Mark");
        putMarkButton.addActionListener(e -> {
        	String ID = JOptionPane.showInputDialog("Enter Student ID :");
        	int marktype = 1;
        	String[] mts = {"First Attestation","Second Attestation","Final"};
            String mt = (String) JOptionPane.showInputDialog(
                null,
                "Select Mark Type:",
                "Mark Type",
                JOptionPane.PLAIN_MESSAGE,
                null,
                mts,
                mts[0]
            );
        	double point = Double.parseDouble(JOptionPane.showInputDialog("Enter double point:")); 
        	int semester = Integer.parseInt(JOptionPane.showInputDialog("Enter semester number :"));
        	if(mt == "First Attestation") {
        		marktype = 1;
        	}
        	else if(mt == "Second Attestation") {
        		marktype = 2;
        	}
        	else {
        		marktype = 3;
        	}
        	((Teacher)currentUser).putMark(ID,marktype,point,semester);
        });
        mainPanel.add(putMarkButton);
        
        JButton sendComplaintsButton = new JButton("sendComplaint");
        sendComplaintsButton.addActionListener(e -> {
        	UrgencyLevel[] urgencyLevels = UrgencyLevel.values();
            UrgencyLevel urlevel = (UrgencyLevel) JOptionPane.showInputDialog(
                null,
                "Select Urgency Level:",
                "Urgency Level",
                JOptionPane.PLAIN_MESSAGE,
                null,
                urgencyLevels,
                urgencyLevels[0]
            );
        	String description = JOptionPane.showInputDialog("Enter description :"); 
        	String IDofStudent = JOptionPane.showInputDialog("Enter Student ID :");
        
        	((Teacher)currentUser).sendComplaints(urlevel,description,IDofStudent);
        });
        mainPanel.add(sendComplaintsButton);
        
        
        return mainPanel;
    }
    private JPanel researcherInterface() {
    	JPanel mainPanel = new JPanel();
    	
    	JButton addResearchButton = new JButton("addResearch");
    	addResearchButton.addActionListener(e -> {
    		String topic = JOptionPane.showInputDialog("Enter topic : ");
    		((CanBeResearcher)currentUser).addResearch(topic);
    	});
    	mainPanel.add(addResearchButton);
    	
    	JButton viewHindButton = new JButton("viewHind");
    	viewHindButton.addActionListener(e -> {
    		System.out.println(" Current H index is " + ((CanBeResearcher)currentUser).getHind());
    	});
    	mainPanel.add(viewHindButton);
    	 
    	JButton addPaperButton = new JButton("addPaper");
    	addPaperButton.addActionListener(e -> {
    		String topic = JOptionPane.showInputDialog("Enter topic : ");
    		String title = JOptionPane.showInputDialog("Enter title : ");
    		String authors = JOptionPane.showInputDialog("Enter authors : ");
    		String journal = JOptionPane.showInputDialog("Enter journal : ");
    		int pages = Integer.parseInt(JOptionPane.showInputDialog("Enter pages : "));

            LocalDateTime curdate = LocalDateTime.now();
            DateTimeFormatter dates = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            String date = curdate.format(dates);
            
    		((CanBeResearcher)currentUser).addPaper(topic,title,authors,journal,pages,date);
    	});
    	mainPanel.add(addPaperButton);
    	
    	JButton publishPapersButton = new JButton("publishPapers");
    	publishPapersButton.addActionListener(e -> {
    		String topic = JOptionPane.showInputDialog("Enter topic : ");
    		String description = JOptionPane.showInputDialog("Enter description : ");
    		((CanBeResearcher)currentUser).publishPapers(topic,description);
    	});
    	mainPanel.add(publishPapersButton);

    	JButton calculateHindButton = new JButton("calculateHind");
    	calculateHindButton.addActionListener(e -> {
    		((CanBeResearcher)currentUser).calculateHind();
    	});
    	mainPanel.add(calculateHindButton);
    	
    	
        JButton printPapersButton = new JButton("printPapers");
        printPapersButton.addActionListener(e -> {
            Comparator<ResearchPaper> c = null;
            String[] scomparators = {"Sort By Date", "Sort By Citations", "Sort By Article Length"};
            String cstr = (String) JOptionPane.showInputDialog(
                    null,
                    "Select Comparator:",
                    "Comparator",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    scomparators,
                    scomparators[0]
            );

            if (cstr.equals("Sort By Date")) {
                c = Comparators.sortByDate();
            } else if (cstr.equals("Sort By Citations")) {
                c = Comparators.sortByCitations();
            } else if (cstr.equals("Sort By Article Length")) {
                c = Comparators.sortByArticleLength();
            }
            System.out.println(" Papers: ");
            System.out.println(((CanBeResearcher) currentUser).printPapers(c));
        });
        mainPanel.add(printPapersButton);
        
        
        JButton citateButton = new JButton("citate");
        citateButton.addActionListener(e -> {
        	String title = JOptionPane.showInputDialog("Enter title : ");
        	String userID = JOptionPane.showInputDialog("Enter ID of Researcher: ");// better use name and surname 
            if (userID != null && !userID.isEmpty()) {
            	CanBeResearcher r = (CanBeResearcher) db.getUser(userID);
            	if(r.getResearcher()!=null) {
    	    		((CanBeResearcher)currentUser).citate(title,r);
            	}
            } else {
                 System.out.println("No User ID provided for view.");
            }	
        	
    	});
    	mainPanel.add(citateButton);
        
        JButton getCitationButton = new JButton("getCitation");
        getCitationButton.addActionListener(e -> {
        	String title = JOptionPane.showInputDialog("Enter title : ");
        	Format[] fs = Format.values();
        	Format f = (Format) JOptionPane.showInputDialog(
        			null,
                    "Select Format:",
                    "Format",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    fs,
                    fs[0]
                );
    		System.out.println(((CanBeResearcher)currentUser).getCitation(title,f));
    	});
    	mainPanel.add(getCitationButton);
        
    	return mainPanel ;
    }
    
    private void studentInterface(JPanel mainPanel){
    	
    	JButton viewLessonsButton = new JButton("viewLessons");
    	viewLessonsButton.addActionListener(e -> {
    		((Bachelor)currentUser).viewLessons();
    	});
    	mainPanel.add(viewLessonsButton);
    	
    	JButton registerForCourseButton = new JButton("registerForCourse");
    	registerForCourseButton.addActionListener(e -> {
    		String cn = JOptionPane.showInputDialog("Enter Course name :"); 
    		((Bachelor)currentUser).registerForCourse(cn);
    	});
    	mainPanel.add(registerForCourseButton);
    	
    	JButton rateTeacherButton = new JButton("rateTeacher");
    	rateTeacherButton.addActionListener(e -> {
    		String ID = JOptionPane.showInputDialog("Enter Teachers ID :"); 
    		int rate = Integer.parseInt(JOptionPane.showInputDialog("Enter int rate :")); 
    		System.out.println(((Bachelor)currentUser).rateTeacher(ID, rate));
    	});
    	mainPanel.add(rateTeacherButton);
    	
    	JButton setStudentOrganizationButton = new JButton("setStudentOrganization");
    	setStudentOrganizationButton.addActionListener(e -> {
    		String storg = JOptionPane.showInputDialog("Enter Student Organization :"); 
    		((Bachelor)currentUser).setStudentOrganization(storg);
    	});
    	mainPanel.add(setStudentOrganizationButton);
    	
    	JButton viewMarksButton = new JButton("viewMarks");
    	viewMarksButton.addActionListener(e -> {
    		int semester = Integer.parseInt(JOptionPane.showInputDialog("Enter semester :")); 
    		System.out.println(((Bachelor)currentUser).viewMarks(semester));
    	});
    	mainPanel.add(viewMarksButton);
    	
    	JButton viewInfoAboutTeacherButton = new JButton("viewInfoAboutTeacher");
    	viewInfoAboutTeacherButton.addActionListener(e -> {
    		String ID = JOptionPane.showInputDialog("Enter Teachers ID :"); 
    		System.out.println(((Bachelor)currentUser).viewInfoAboutTeacher(ID));
    	});
    	mainPanel.add(viewInfoAboutTeacherButton);
    	
    	JButton viewTranscriptButton = new JButton("viewTranscript");
    	viewTranscriptButton.addActionListener(e -> {
    		System.out.println(((Bachelor)currentUser).viewTranscript());
    	});
    	mainPanel.add(viewTranscriptButton);
    	
    	JButton viewCoursesButton = new JButton("viewCourses");
    	viewCoursesButton.addActionListener(e -> {
    		System.out.println(((Bachelor)currentUser).viewCourses());
    	});
    	mainPanel.add(viewCoursesButton);
    }
    
    private JPanel createBachelorPanel() {
        JPanel mainPanel = new JPanel();
        JLabel label = new JLabel("Welcome, Bachelor!");
        mainPanel.add(label);
        studentInterface(mainPanel);
        
        return mainPanel;
    }
    private JPanel createGraduateStudentPanel() {
        JPanel mainPanel = new JPanel();
        JLabel label = new JLabel("Welcome, Graduate Student!");
        mainPanel.add(label);
        studentInterface(mainPanel);
        
    	JButton setSupervisorButton = new JButton("setSupervisor");
    	setSupervisorButton.addActionListener(e -> {
    		String ID = JOptionPane.showInputDialog("Enter Researchers ID : ");
    		CanBeResearcher supervisor = (CanBeResearcher) db.getUser(ID);
    		try {
				((GraduateStudent)currentUser).setSupervisor(supervisor);
			} catch (LowHIndexSupervisorException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	});
    	mainPanel.add(setSupervisorButton);
    	
        return mainPanel;
    }
    private JPanel createTechSupportSpecialistPanel() {
        JPanel mainPanel = new JPanel();
        JLabel label = new JLabel("Welcome, TechSupportSpecialist!");
        mainPanel.add(label);
        
        JButton viewOrdersButton = new JButton("View All Orders");
        viewOrdersButton.addActionListener(e -> {
            List<Order> orders = ((TechSupportSpecialist) currentUser).viewOrders();
            if (orders == null || orders.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No orders available.");
            } else {
                Object[] options = orders.toArray();
                Order selectedOrder = (Order) JOptionPane.showInputDialog(
                    null,
                    "Select a order:",
                    "Orders",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
                );

                if (selectedOrder != null) {
                    handleOrder(selectedOrder);
                }
            }
        });
        mainPanel.add(viewOrdersButton);
        
        return mainPanel;
	}
    private void handleOrder(Order selectedOrder) {
    	int choice = JOptionPane.showOptionDialog(
    			null,
    			"Do you want to accept or reject this order?",
    			"Order Action",
    			JOptionPane.YES_NO_OPTION,
    			JOptionPane.QUESTION_MESSAGE,
    			null,
    			new String[]{"Accept", "Reject"},
    			"Accept"
    	);

    	if (choice == JOptionPane.YES_OPTION) {
    		System.out.println(((TechSupportSpecialist) currentUser).acceptOrder(selectedOrder));
    	} else if (choice == JOptionPane.NO_OPTION) {
    		System.out.println(((TechSupportSpecialist) currentUser).rejectOrder(selectedOrder));
    	}
	}
	public boolean login(String userID, String password) {
        User user = db.getUser(userID);
        if (user != null && user.login(userID, password)) {
            currentUser = user;
            return true;
        }
        return false;
    } 
    public JPanel mainPanel() {
        if (currentUser == null) {
            System.out.println("You did not log");
            return null;
        }
        if (currentUser instanceof Dean) {
        	return createDeanPanel();
        } else if (currentUser instanceof Admin) {
        	return createAdminPanel();
        } else if (currentUser instanceof Manager) {
        	return createManagerPanel();
        } else if (currentUser instanceof Teacher) {
        	return createTeacherPanel();
        } else if (currentUser instanceof Bachelor && !(currentUser instanceof GraduateStudent)) {
        	return createBachelorPanel();
        } else if (currentUser instanceof GraduateStudent) {
        	return createGraduateStudentPanel();
        } else if (currentUser instanceof TechSupportSpecialist) {
        	return createTechSupportSpecialistPanel();
        }
        return null; 
    }
    
	public void logout() {
        currentUser = null;
    }
}
