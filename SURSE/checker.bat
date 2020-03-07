javac *.java

java Test "VMStests/test00/input/campaigns.txt" "VMStests/test00/input/users.txt" "VMStests/test00/input/events.txt" > output0.txt
java Test "VMStests/test01/input/campaigns.txt" "VMStests/test01/input/users.txt" "VMStests/test01/input/events.txt" > output1.txt
java Test "VMStests/test02/input/campaigns.txt" "VMStests/test02/input/users.txt" "VMStests/test02/input/events.txt" > output2.txt
java Test "VMStests/test03/input/campaigns.txt" "VMStests/test03/input/users.txt" "VMStests/test03/input/events.txt" > output3.txt
java Test "VMStests/test04/input/campaigns.txt" "VMStests/test04/input/users.txt" "VMStests/test04/input/events.txt" > output4.txt
java Test "VMStests/test05/input/campaigns.txt" "VMStests/test05/input/users.txt" "VMStests/test05/input/events.txt" > output5.txt
java Test "VMStests/test06/input/campaigns.txt" "VMStests/test06/input/users.txt" "VMStests/test06/input/events.txt" > output6.txt
java Test "VMStests/test07/input/campaigns.txt" "VMStests/test07/input/users.txt" "VMStests/test07/input/events.txt" > output7.txt
java Test "VMStests/test08/input/campaigns.txt" "VMStests/test08/input/users.txt" "VMStests/test08/input/events.txt" > output8.txt
java Test "VMStests/test09/input/campaigns.txt" "VMStests/test09/input/users.txt" "VMStests/test09/input/events.txt" > output9.txt

echo "Outputs are available in output*.txt files"

del *.class

pause