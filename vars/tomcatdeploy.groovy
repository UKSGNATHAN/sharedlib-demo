def call(config){
  def credId = config['credId']
  def ip = config['ip']
  def userName = config['userName']
  def tomcatHome = config['tomcatHome']
  
  sshagent([credId]) {
                    // Stop the tomcat //
                    sh "ssh -o StrictHostKeyChecking=no ${userName}@${ip} ${tomcatHome}/bin/shutdown.sh"
                    // Delete old warfile //
                    sh "ssh ${userName}@${ip} rm -rf ${tomcatHome}/webapps/${warName}*"
                    // Copy latest war file to tomcat-dev server //
                    sh "scp target/springvmvc.war ${userName}@${ip}:${tomcatHome}/webapps/"
                    // start the server //
                    sh "ssh ${userName}@${ip} ${tomcatHome}/bin/startup.sh"
   }
 }
              
                 
