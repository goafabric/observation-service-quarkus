create table vital_sign (
(
	id varchar(36) not null
		constraint pk_audit
			primary key,

    patient_id varchar(36),
    version bigint default 0,

    type varchar(255),
    created_at timestamp,
    content text
);

-- Optional: create index on patient_id for faster lookups
CREATE INDEX idx_vital_sign_patient_id ON vital_sign(patient_id);