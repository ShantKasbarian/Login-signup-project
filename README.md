How to use this application:
1 - create a database
2 - in application properties change the following configurations: (
    a - server port(optional)
    b - datasource.url to your datasource's url
    c - datasource.username to your datasource's username
    d - datasource.password to your datasource's password
    e - if you're using a different RDBMS than the one listed then change it to your RDBMS's driverClassName (spring.datasource.driverClassName= YOUR_RDBMS'S_DRIVER_CLASS_NAME)
        and hibernateDialect (org.hibernate.dialect=org.hibernate.dialect.YOUR_RDBMS'S_DIALECT)
)
And you're done :)
