curl   http://127.0.0.1:10001/lift
curl   http://127.0.0.1:10002/lift
curl   http://127.0.0.1:10000/lift
curl -X POST -H "Content-Type: text/plain"--data "123458" http://127.0.0.1:10000/dial

curl -X POST -H "Content-Type: text/plain" --data "123456" http://127.0.0.1:10002/drop
curl   http://127.0.0.1:10002/lift
curl -X POST -H "Content-Type: text/plain" --data "123458" http://127.0.0.1:10001/dial
curl -X POST -H "Content-Type: text/plain" "123458" http://127.0.0.1:10001/drop
