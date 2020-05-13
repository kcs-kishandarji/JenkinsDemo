BUILD_ID=do_not_kill_me
nohup nice java -jar target/JenkinsDemo-1.0-SNAPSHOT.jar > test.log 2>&1 &
# run tail -f in background
tail -f test.log > out 2>&1 &

# process id of tail command
tailpid=$!

# wait for sometime
sleep 10

# now kill the tail process
kill $tailpid

exit 