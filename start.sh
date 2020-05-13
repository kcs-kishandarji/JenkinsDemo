BUILD_ID=do_not_kill_me
nohup nice java -jar target/JenkinsDemo-1.0-SNAPSHOT.jar > test.log 2>&1 &
less +F test.log 