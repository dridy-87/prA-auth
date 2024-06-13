## 소개
- 프로젝트 : springboot + redis 인증만 담당하는 서비스

## Redis 구동

레디스 도커 이미지 다운로드
```sh
docker pull redis
```
test-redis 이름으로 컨테이너 구동 포트는 6379 
```sh
docker run --name test-redis -p 6379:6379 -d redis 
```
컨테이너 접속
```sh
docker exec -it test-redis /bin/bash
```
접속한 컨테이너 redis 접속
```sh
redis-cli
```
redis-cli 간단한 명령어
```sh
Keys *  // 전체 조회
flushall // 모든 데이터 삭제
```