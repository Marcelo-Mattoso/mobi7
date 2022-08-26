# mobi7
Preparativo para Code Interview - Backend

Postman JSON:
{
	"info": {
		"_postman_id": "fc555438-84da-4a50-a06f-def876c877b7",
		"name": "Task",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
	},
	"item": [
		{
			"name": "Base",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "formdata",
					"formdata": [
						{
							"key": "file",
							"type": "file",
							"src": "/C:/Users/mmatt/Downloads/base.csv"
						}
					]
				},
				"url": {
					"raw": "http://localhost:8080/api/csv/uploadBase",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"csv",
						"uploadBase"
					]
				}
			},
			"response": []
		},
		{
			"name": "Posicoes",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "formdata",
					"formdata": [
						{
							"key": "file",
							"type": "file",
							"src": "/C:/Users/mmatt/Downloads/posicoes.csv"
						}
					]
				},
				"url": {
					"raw": "http://localhost:8080/api/csv/uploadPosicoes",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"csv",
						"uploadPosicoes"
					]
				}
			},
			"response": []
		},
		{
			"name": "Posicoes",
			"protocolProfileBehavior": {
				"disableBodyPruning": true
			},
			"request": {
				"method": "GET",
				"header": [],
				"body": {
					"mode": "formdata",
					"formdata": []
				},
				"url": {
					"raw": "http://localhost:8080/api/csv/listposicoes",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"csv",
						"listposicoes"
					]
				}
			},
			"response": []
		},
		{
			"name": "bases",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/csv/listbases",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"csv",
						"listbases"
					]
				}
			},
			"response": []
		},
		{
			"name": "getPositionByAreaName",
			"protocolProfileBehavior": {
				"disableBodyPruning": true
			},
			"request": {
				"method": "GET",
				"header": [],
				"body": {
					"mode": "formdata",
					"formdata": []
				},
				"url": {
					"raw": "http://localhost:8080/api/csv/posicaoinarea/?area=ponto 2&placa=TESTE001",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"csv",
						"posicaoinarea",
						""
					],
					"query": [
						{
							"key": "area",
							"value": "ponto 2"
						},
						{
							"key": "placa",
							"value": "TESTE001"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "getbasebyname",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/csv/getbasebyname?area=ponto 1",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"csv",
						"getbasebyname"
					],
					"query": [
						{
							"key": "area",
							"value": "ponto 1"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "getcarrobyplaca",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/csv/getcarrobyplaca?placa=TESTE001",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"csv",
						"getcarrobyplaca"
					],
					"query": [
						{
							"key": "placa",
							"value": "TESTE001"
						}
					]
				}
			},
			"response": []
		}
	]
}
