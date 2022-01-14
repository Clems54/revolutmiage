INSERT INTO Utilisateur (numero_passeport, date_de_naissance, nom, numero_telephone, pays, prenom, secret)
VALUES ('76TE87654', '1990-01-01', 'Registre', '+33643156839', 'France', 'Jean',
        '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6'),
       ('22OP49977', '1971-09-07', 'Ratif', '+3301020304', 'France', 'Luc',
        '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6'),
       ('00CV93645', '1991-12-30', 'Prane', '+33612469800', 'France', 'Dolly',
        '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6');

INSERT INTO Compte (iban, solde, utilisateur_numero_passeport, pays)
VALUES ('FR2030003000701632956183R61', 5000000, null, 'FRANCE'),
       ('FR0217569000506965351885H25', 3000, '76TE87654', 'FRANCE'),
       ('FR0317569000507363899875J51', 4500, '22OP49977', 'FRANCE'),
       ('FR6330003000403931818847A71', 1000, '00CV93645', 'FRANCE');

INSERT INTO Carte (numero_carte, bloquee, code, compte_iban, cryptogramme, localisation, plafond, sans_contact, virtuelle)
VALUES ('8374651423587600', false, '0000', 'FR0217569000506965351885H25', '666', false, 2000, false, false),
       ('8475623509125489', false, '5555', 'FR0317569000507363899875J51', '700', false, 1250, true, false),
       ('1295736253670920', false, '5400', 'FR6330003000403931818847A71', '500', false, 2500, true, false);

INSERT INTO Utilisateur_Comptes (utilisateur_numero_passeport, comptes_iban)
VALUES ('76TE87654', 'FR0217569000506965351885H25'),
       ('22OP49977', 'FR0317569000507363899875J51'),
       ('00CV93645', 'FR6330003000403931818847A71');

INSERT INTO Taux (source, destination, taux)
VALUES ('FRANCE', 'USA', 1.14),
       ('USA', 'FRANCE', 0.88)
