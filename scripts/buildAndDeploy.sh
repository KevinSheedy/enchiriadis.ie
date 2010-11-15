set -o verbose #echo on
#set +o verbose #echo off

appDir="C:/dev/enchiriadis.ie"
warName=ROOT.war
warFilePath=./target/$warName
destDir=/opt/tomcat6/webapps/enchiriadis.ie
user=root
url="kevinsheedy.virtual.vps-host.net"



cd $appDir
grails clean
grails compile
grails war $warFilePath
scp $warFilePath $user@$url:$destDir
ssh $user@$url "/opt/tomcat6/bin/shutdown.sh"
ssh $user@$url "rm -R -f /opt/tomcat6/webapps/enchiriadis.ie/ROOT"
ssh $user@$url "/opt/tomcat6/bin/startup.sh"
