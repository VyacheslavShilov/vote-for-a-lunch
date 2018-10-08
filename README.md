[![Codacy Badge](https://api.codacy.com/project/badge/Grade/7dc5d1e04cd64fb581c245aae4e40149)](https://www.codacy.com/app/VyacheslavShilov/vote-for-a-lunch?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=VyacheslavShilov/vote-for-a-lunch&amp;utm_campaign=Badge_Grade)
![Build Status](https://travis-ci.org/VyacheslavShilov/vote-for-a-lunch.svg?branch=master)

## Выпускной проект TopJava14

#####Использованный стек технологий: Spring MVC, REST, Spring Security, Spring Data JPA, Hibernate ORM, SLF4J, Json Jackson, Apache Tomcat, Ehcache, HSQLDB, JUnit.


###Реализована REST система голосования для принятия решения, где пообедать

> - Типы пользователей: администратор и постоянные пользователи
> - Администратор может добавлять ресторан и дневное обеденное меню(обычно 2-5 блюд, только название и цена)
> - Меню меняется каждый день (администратор вносит изменения)
> - Пользователи могут проголосовать, в каком ресторане они хотят пообедать
> - Для каждого пользователя учитывается только один голос
> - Если пользователь снова проголосовал в тот же день:
>  - Если до 11:00 то мы предполагаем, что он передумал,
>  - Если после 11:00 то голос не меняется.
