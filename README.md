# Teaching-HEIGVD-RES-2016-Labo-SMTP

## CIANI Antony, HERNANDEZ Thomas


## Description

This project is an implementation of a client application (TCP) in Java that automatically plays pranks on a list of victims. The goal is to make random groups of a list of email addresses that are passed to the application and to select a random message and send it to the groups via email. A partial implementation of the SMTP protocol is used to communicate with a SMTP server through Sockets to send the prank emails generated by our application.

Basically, you can edit the emails.txt file with your own mailing list, which will define the list of victims. You can also edit the messages.txt file with your own messages. When a prank is played, one of this message is selected to be the data of your mail. You can set the properties of the applicatoin to define how many groups of victims should be formed and which server should be used to send the emails. N.B : For each group, there is 1 sender and at least 2 recipients hence at least 3 emails per group you want to form.

## Example

Here is a simple exemple: consider that the program generates a group G from a list of 4 victims. The group sender is Bob and the recipients are Alice, Mallory and Jean-Kevin. When the prank is played on group G, then one of the fake messages is picked. The program communicates with a SMTP server so that Alice, Mallory and Jean-Kevin receive a mail, which appears to be sent by Bob.

## Configuration

In order to run a prank campaign, you need to configure the following files:

- **appconfig.properties**

	this file contains the following fields:

	![protocol](figures/protocol.PNG)

	*smtpserveraddress*: the address of the SMTP server you want to use
	
	*serverport*: the port's number you want to connect to, in order to reach the SMTP server.

	*nbgroups*: the number of group you want

	*emailspath*: the file that contains your victims' mails.

	*messagespath*: the file that contains the text of the mails.

- **emails.txt**

	this file contains the e-mail addresses of your victims. You just need to write your own addresses. The only condition is to write one address per line.

- **messages.txt**

	this files contains the messages you will send to your victims. You write your own messages but the file must have this format:

	![messages](figures/messages.PNG)

	where the first line is the subject and each message must be seperated with ////

## Implementation

Here below you can see the class diagram of our implementation:

![uml](figures/uml.jpg)

- *Message*: it simply represents an email message (Subject + message).

- *AppConfigurator*: it retrieves the messages and the emails from the corresponding files and also creates a certain number of groups. Thus, it generates the informations needed for a prank.

Moreover, the next two classes need a special focus regarding their responsibilities in our implementation:

- *PrankMailGenerator*: the main program who first reads the configuration file and do the setup. Then, it forms random groups depending on the properties and if the number of emails addresses is not enough (less than 3) it stops and warns the user. Finally, a prank is generated with a random message and sent with our SMTP client.

- *ClientSMTP*: allows a connection to a SMTP server in order to send emails. Once connected, our SMTP protocol "like" is used to perform the job and verify its good behavior.

Here below you can see an exemple of dialogue between our client and a SMTP server:

![dialogue](figures/dialogue.PNG)
  


## Installing and using a mock SMTP server


If you want to experiment with our tool, without "really" sending mails immediately, you can first try it with a mock SMTP server. It means that you will be connected to this server instead of a "real" one. Thus, your victims won't receive your emails, but you will be able to read them and check if everything is ready for your prank.

**How to install**

1. Click on this link: [https://github.com/tweakers-dev/MockMock](https://github.com/tweakers-dev/MockMock)
2. Download the MockMock.jar
3. Unzip it! (remember the directory)

**How to use**

1. Open a terminal and go to the directory where you have unzipped the .jar
2. Run the following command: `java -jar MockMock.jar `
3. 


