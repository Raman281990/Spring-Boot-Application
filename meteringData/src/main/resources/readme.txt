Steps to use the application.

1)Build the maven project.
2) Run the EnergyConsumptionApplication main class as java application.
3) Cache has been used to persist the data in the application.

API's for Rule Set

1) http://localhost:8080/ruleSet/save
	RequestMethod = POST
	
	This API is for saving the Rule Set in the Energy Consumption application.
	
	Request:
	{
  "ruleSetData": [
  {
    "month": "JAN",
    "profile": "A",
    "fraction":"0.2"
  },
  {
    "month": "FEB",
    "profile": "A",
    "fraction":"0.1"
  },
  {
    "month": "MAR",
    "profile": "A",
    "fraction":"0.13"
  },
  {
    "month": "APR",
    "profile": "A",
    "fraction":"0.08"
  },
  {
    "month": "MAY",
    "profile": "A",
    "fraction":"0.08"
  },
  {
    "month": "JUN",
    "profile": "A",
    "fraction":"0"
  },
  {
    "month": "JUL",
    "profile": "A",
    "fraction":"0"
  },
  {
    "month": "AUG",
    "profile": "A",
    "fraction":"0.01"
  },
  {
    "month": "SEP",
    "profile": "A",
    "fraction":"0.09"
  },
  {
    "month": "OCT",
    "profile": "A",
    "fraction":"0.04"
  },
  {
    "month": "NOV",
    "profile": "A",
    "fraction":"0.1"
  },
  {
    "month": "DEC",
    "profile": "A",
    "fraction":"0.17"
  }
]
}

This is valid data


For invalid data:

{
  "ruleSetData": [
  {
    "month": "JAN",
    "profile": "A",
    "fraction":"0.2"
  },
  {
    "month": "FEB",
    "profile": "A",
    "fraction":"0.1"
  },
  {
    "month": "MAR",
    "profile": "A",
    "fraction":"0.13"
  },
  {
    "month": "APR",
    "profile": "A",
    "fraction":"0.08"
  },
  {
    "month": "MAY",
    "profile": "A",
    "fraction":"0.08"
  },
  {
    "month": "JUN",
    "profile": "A",
    "fraction":"0"
  },
  {
    "month": "JUL",
    "profile": "A",
    "fraction":"0"
  },
  {
    "month": "AUG",
    "profile": "A",
    "fraction":"0.01"
  },
  {
    "month": "SEP",
    "profile": "A",
    "fraction":"0.09"
  },
  {
    "month": "OCT",
    "profile": "A",
    "fraction":"0.04"
  },
  {
    "month": "NOV",
    "profile": "A",
    "fraction":"0.1"
  },
  {
    "month": "DEC",
    "profile": "A",
    "fraction":"0.18"
  }
]
} 

Error will be raised in this case.


2)http://localhost:8080/ruleSet/read?profile=A&month=JAN  API for fecthing Rule Set
  RequestMethod = GET
For a invalid request: API will give 204 (NO_DATA_FOUND) response

For a valid request: Response: {"fraction":"0.2","errors":null}


3) http://localhost:8080/ruleSet/delete
  RequestMethod = DELETE
 API deletes Rule Set from the application data.

Response: {
    "success": true,
    "errors": null
}  


4)  http://localhost:8080/ruleSet/update
	RequestMethod = PUT
	
	Valid :Request
	
	{
  "ruleSetData": [
  {
    "month": "JAN",
    "profile": "A",
    "fraction":"0.2"
  },
  {
    "month": "FEB",
    "profile": "A",
    "fraction":"0.1"
  },
  {
    "month": "MAR",
    "profile": "A",
    "fraction":"0.13"
  },
  {
    "month": "APR",
    "profile": "A",
    "fraction":"0.08"
  },
  {
    "month": "MAY",
    "profile": "A",
    "fraction":"0.08"
  },
  {
    "month": "JUN",
    "profile": "A",
    "fraction":"0"
  },
  {
    "month": "JUL",
    "profile": "A",
    "fraction":"0"
  },
  {
    "month": "AUG",
    "profile": "A",
    "fraction":"0.01"
  },
  {
    "month": "SEP",
    "profile": "A",
    "fraction":"0.09"
  },
  {
    "month": "OCT",
    "profile": "A",
    "fraction":"0.04"
  },
  {
    "month": "NOV",
    "profile": "A",
    "fraction":"0.1"
  },
  {
    "month": "DEC",
    "profile": "A",
    "fraction":"0.17"
  }
]
}


Response:

{
    "success": true,
    "errors": null
}




  Saving Meter Readings
  
  1)http://localhost:8080/meterReading/save 
  
	This API saves Customer data.
	Request Method = POST
  
  Request:
  
  {
  "meterReadingData": [
  {
    "meterId": "0001",
    "profile": "A",
    "month":"JAN",
    "meterReading":7
  },
  {
    "meterId": "0001",
    "profile": "A",
    "month":"FEB",
    "meterReading":9
  },
  {
    "meterId": "0001",
    "profile": "A",
    "month":"MAR",
    "meterReading":11
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"APR",
    "meterReading":13
  },
  {
    "meterId": "0001",
    "profile": "A",
    "month":"MAY",
    "meterReading":15
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"JUN",
    "meterReading":17
  },
  {
    "meterId": "0001",
    "profile": "A",
    "month":"JUL",
    "meterReading":19
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"AUG",
    "meterReading":21
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"SEP",
    "meterReading":25
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"OCT",
    "meterReading":27
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"NOV",
    "meterReading":29
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"DEC",
    "meterReading":35
  }
]
}


Response:
{"errors":null,"success":true}


In case of invalid request  Error will be raised

Invalid request:

{
  "meterReadingData": [
  {
    "meterId": "0001",
    "profile": "A",
    "month":"JAN",
    "meterReading":5
  },
  {
    "meterId": "0001",
    "profile": "A",
    "month":"FEB",
    "meterReading":7
  },
  {
    "meterId": "0001",
    "profile": "A",
    "month":"MAR",
    "meterReading":9
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"APR",
    "meterReading":12
  },
  {
    "meterId": "0001",
    "profile": "A",
    "month":"MAY",
    "meterReading":15
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"JUN",
    "meterReading":17
  },
  {
    "meterId": "0001",
    "profile": "A",
    "month":"JUL",
    "meterReading":19
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"AUG",
    "meterReading":21
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"SEP",
    "meterReading":25
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"OCT",
    "meterReading":27
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"NOV",
    "meterReading":29
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"DEC",
    "meterReading":35
  }
]
}

Response: {"errors":[{"message":"Consumption value is not permissible for a month for 0001And Month JAN"}],"success":false}

2) http://localhost:8080/meterReading/fetch?meterId=0001&month=JAN
	This API fetches meter readings
	RequestMethod=GET
	
	Response:
	{"profile":"A","meterReading":7.0,"businessError":null}
	
	For Invalid request: 204 HttpStatus NO data Found
	
3) http://localhost:8080/meterReading/evict
	
	This API evicts meter reading data.
	RequestMethod=DELETE
	
	Request:
	
	{
    "meterId": "0001",
   
    "month":"JAN"
   
  }
  Response:
  {
    "success": true
}

4) http://localhost:8080/meterReading/update

	This API updates meter readings in the application.
	RequestMethod = PUT
	
	Request:
	
	{
  "meterReadingData": [
  {
    "meterId": "0001",
    "profile": "A",
    "month":"JAN",
    "meterReading":7
  },
  {
    "meterId": "0001",
    "profile": "A",
    "month":"FEB",
    "meterReading":9
  },
  {
    "meterId": "0001",
    "profile": "A",
    "month":"MAR",
    "meterReading":11
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"APR",
    "meterReading":13
  },
  {
    "meterId": "0001",
    "profile": "A",
    "month":"MAY",
    "meterReading":15
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"JUN",
    "meterReading":17
  },
  {
    "meterId": "0001",
    "profile": "A",
    "month":"JUL",
    "meterReading":19
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"AUG",
    "meterReading":21
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"SEP",
    "meterReading":25
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"OCT",
    "meterReading":27
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"NOV",
    "meterReading":29
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"DEC",
    "meterReading":35
  }
]
}

Response:
{
    "errors": null,
    "success": true
}



Request:

{
  "meterReadingData": [
  {
    "meterId": "0001",
    "profile": "A",
    "month":"JAN",
    "meterReading":7
  },
  {
    "meterId": "0001",
    "profile": "A",
    "month":"FEB",
    "meterReading":9
  },
  {
    "meterId": "0001",
    "profile": "A",
    "month":"MAR",
    "meterReading":11
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"APR",
    "meterReading":13
  },
  {
    "meterId": "0001",
    "profile": "A",
    "month":"MAY",
    "meterReading":15
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"JUN",
    "meterReading":17
  },
  {
    "meterId": "0001",
    "profile": "A",
    "month":"JUL",
    "meterReading":19
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"AUG",
    "meterReading":21
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"SEP",
    "meterReading":25
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"OCT",
    "meterReading":27
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"NOV",
    "meterReading":29
  },
  {
     "meterId": "0001",
    "profile": "A",
    "month":"DEC",
    "meterReading":35
  },
   {
    "meterId": "0002",
    "profile": "A",
    "month":"JAN",
    "meterReading":5
  },
  {
    "meterId": "0002",
    "profile": "A",
    "month":"FEB",
    "meterReading":7
  },
  {
    "meterId": "0002",
    "profile": "A",
    "month":"MAR",
    "meterReading":9
  },
  {
     "meterId": "0002",
    "profile": "A",
    "month":"APR",
    "meterReading":12
  },
  {
    "meterId": "0002",
    "profile": "A",
    "month":"MAY",
    "meterReading":15
  },
  {
     "meterId": "0002",
    "profile": "A",
    "month":"JUN",
    "meterReading":17
  },
  {
    "meterId": "0002",
    "profile": "A",
    "month":"JUL",
    "meterReading":19
  },
  {
     "meterId": "0002",
    "profile": "A",
    "month":"AUG",
    "meterReading":21
  },
  {
     "meterId": "0002",
    "profile": "A",
    "month":"SEP",
    "meterReading":25
  },
  {
     "meterId": "0002",
    "profile": "A",
    "month":"OCT",
    "meterReading":27
  },
  {
     "meterId": "0002",
    "profile": "A",
    "month":"NOV",
    "meterReading":29
  },
  {
     "meterId": "0002",
    "profile": "A",
    "month":"DEC",
    "meterReading":35
  }
 
]
}



4) http://localhost:8080/consumption/retrieve?meterId=0001&month=JAN
	API for retriving consumption value.
	RequestMethod=GET
	
	
	Response:
	
	
	{"consumption":7.0,"message":null}
	
	
Important Information:	
	
1) Documentation of API is in swagger

PFB the swagger link:
http://localhost:8080/swagger-ui.html

Code coverage:

2) Navigate to the below location:

D:\workspace\meteringData\meteringData\target\site\jacoco\index.html

for code coverage


3) Validator framework has been used for performing validations.
com.process.energy.consumption.validator --classes in the validator package	