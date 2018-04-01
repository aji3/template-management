CREATE SCHEMA IF NOT EXISTS `TemplateManagement`;

CREATE TABLE IF NOT EXISTS `TemplateManagement`.`Template` (
    `templateId` int(11) NOT NULL,
    `name` varchar(128),
    `description` varchar(1024),
    `path` varchar(512),
    `content` varchar(1000000),
    `contentType` char(20),
    `sampleDataJsonStr` varchar(10000),
    `createdTimestamp` datetime,
    `createdBy` varchar(255),
    `updatedTimestamp` datetime,
    `updatedBy` varchar(255),
    PRIMARY KEY (`templateId`)
);
CREATE TABLE IF NOT EXISTS `TemplateManagement`.`TemplateParameter` (
    `templateId` int(11) NOT NULL,
    `parameterId` int(11),
    `parameterName` varchar(255),
    `createdTimestamp` datetime,
    `createdBy` varchar(255),
    `updatedTimestamp` datetime,
    `updatedBy` varchar(255),
    PRIMARY KEY (`templateId`,`parameterId`)
);
 
