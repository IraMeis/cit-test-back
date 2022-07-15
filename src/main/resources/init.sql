CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table dict_task_substring_element_types
(
    unique_id          bigserial
        constraint dict_task_substring_element_types_pkey
            primary key,
    uuid               uuid                     default uuid_generate_v4()    not null,
    created_timestamp  timestamp with time zone default statement_timestamp() not null,
    modified_timestamp timestamp with time zone default statement_timestamp() not null,
    is_deleted         boolean                  default false,
    name               varchar,
    code               bigint                                                 not null
        constraint dict_task_substring_element_types_code_key
            unique,
    description        varchar
);

comment on table dict_task_substring_element_types is 'Таблица, содержащая словарные значения типа элемента задачи подстроки';

comment on column dict_task_substring_element_types.unique_id is 'Идентификатор записи. Первичный ключ';

comment on column dict_task_substring_element_types.uuid is 'uuid объекта';

comment on column dict_task_substring_element_types.created_timestamp is 'Дата и время создания записи';

comment on column dict_task_substring_element_types.modified_timestamp is 'Дата и время последнего изменения записи';

comment on column dict_task_substring_element_types.is_deleted is 'Признак удалённой записи';

comment on column dict_task_substring_element_types.name is 'Наименование';

comment on column dict_task_substring_element_types.code is 'Код записи';

comment on column dict_task_substring_element_types.description is 'Описание';

create table tasks_substring
(
    unique_id          bigserial
        constraint tasks_substring_pkey
            primary key,
    uuid               uuid                     default uuid_generate_v4()    not null,
    created_timestamp  timestamp with time zone default statement_timestamp() not null,
    modified_timestamp timestamp with time zone default statement_timestamp() not null,
    is_deleted         boolean                  default false
);

comment on table tasks_substring is 'Таблица, содержащая задачи подстрок';

comment on column tasks_substring.unique_id is 'Идентификатор записи. Первичный ключ';

comment on column tasks_substring.uuid is 'UUID записи';

comment on column tasks_substring.created_timestamp is 'Дата и время создания записи';

comment on column tasks_substring.modified_timestamp is 'Дата и время последнего изменения записи';

comment on column tasks_substring.is_deleted is 'Признак удалённой записи';

create table tasks_square
(
    unique_id          bigserial
        constraint tasks_square_pkey
            primary key,
    uuid               uuid                     default uuid_generate_v4()    not null,
    created_timestamp  timestamp with time zone default statement_timestamp() not null,
    modified_timestamp timestamp with time zone default statement_timestamp() not null,
    is_deleted         boolean                  default false
);

comment on table tasks_square is 'Таблица, содержащая задачи квадрата';

comment on column tasks_square.unique_id is 'Идентификатор записи. Первичный ключ';

comment on column tasks_square.uuid is 'UUID записи';

comment on column tasks_square.created_timestamp is 'Дата и время создания записи';

comment on column tasks_square.modified_timestamp is 'Дата и время последнего изменения записи';

comment on column tasks_square.is_deleted is 'Признак удалённой записи';

create table task_square_elements
(
    unique_id          bigserial
        constraint task_square_elements_pkey
            primary key,
    uuid               uuid                     default uuid_generate_v4()    not null,
    created_timestamp  timestamp with time zone default statement_timestamp() not null,
    modified_timestamp timestamp with time zone default statement_timestamp() not null,
    is_deleted         boolean                  default false,
    matrix_entry       bigint,
    order_in_list      bigint,
    task_ref           bigint not null
        constraint task_square_elements_task_ref_fkey
            references tasks_square
            on update restrict on delete restrict
);

comment on table task_square_elements is 'Таблица, содержащая элементы массива, ассоциированного с задачей квадрата';

comment on column task_square_elements.unique_id is 'Идентификатор записи. Первичный ключ';

comment on column task_square_elements.uuid is 'uuid объекта';

comment on column task_square_elements.created_timestamp is 'Дата и время создания записи';

comment on column task_square_elements.modified_timestamp is 'Дата и время последнего изменения записи';

comment on column task_square_elements.is_deleted is 'Признак удалённой записи';

comment on column task_square_elements.matrix_entry is 'Элемент массива, ассоциированного с задачей';

comment on column task_square_elements.order_in_list is 'Порядок элемента в массиве';

comment on column task_square_elements.task_ref is 'Ссылка на задачу';

create table task_substring_elements
(
    unique_id          bigserial
        constraint task_substring_elements_pkey
            primary key,
    uuid               uuid                     default uuid_generate_v4()    not null,
    created_timestamp  timestamp with time zone default statement_timestamp() not null,
    modified_timestamp timestamp with time zone default statement_timestamp() not null,
    is_deleted         boolean                  default false,
    string_array_entry varchar,
    element_type       bigint not null
        constraint task_substring_elements_element_type_fkey
            references dict_task_substring_element_types (code)
            on update restrict on delete restrict,
    task_ref           bigint not null
        constraint task_square_elements_task_ref_fkey
            references tasks_substring
            on update restrict on delete restrict
);

comment on table task_substring_elements is 'Таблица, содержащая элементы массива, ассоциированного с задачей подстроки';

comment on column task_substring_elements.unique_id is 'Идентификатор записи. Первичный ключ';

comment on column task_substring_elements.uuid is 'uuid объекта';

comment on column task_substring_elements.created_timestamp is 'Дата и время создания записи';

comment on column task_substring_elements.modified_timestamp is 'Дата и время последнего изменения записи';

comment on column task_substring_elements.is_deleted is 'Признак удалённой записи';

comment on column task_substring_elements.string_array_entry is 'Элемент массива, ассоциированного с задачей';

comment on column task_substring_elements.element_type is 'Ссылка на табличное значение типа элемента';

comment on column task_substring_elements.task_ref is 'Ссылка на задачу';
