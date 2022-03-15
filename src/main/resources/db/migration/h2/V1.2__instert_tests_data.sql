
INSERT INTO offer_country (country_code, country_label) VALUES
      ('FR', 'France'),
      ('CH', 'Suisse')
;


INSERT INTO offer_user (user_name,user_gender, user_phone ,user_country_id, user_birthdate, user_created_at) VALUES
 ( 'Dupont',1, '+33642151212', (select country_id from offer_country where country_code = 'FR'),PARSEDATETIME('20100130','YYYYMMDD'), CURRENT_TIMESTAMP()),
( 'Dupond',1, '+41784745421',(select country_id from offer_country where country_code = 'CH'),PARSEDATETIME('20080130','YYYYMMDD'), CURRENT_TIMESTAMP())
;
