create table dict_task_substring_element_types
(
    unique_id          bigint identity primary key,
    uuid               uuid not null,
    created_timestamp  datetime default CURRENT_DATE,
    modified_timestamp datetime default CURRENT_DATE,
    is_deleted         boolean default false,
    name               varchar(8000),
    code               bigint not null unique,
    description        varchar(8000)
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
    unique_id          bigint identity primary key,
    uuid               uuid not null,
    created_timestamp  datetime default CURRENT_DATE,
    modified_timestamp datetime default CURRENT_DATE,
    is_deleted         boolean default false
);

comment on table tasks_substring is 'Таблица, содержащая задачи подстрок';

comment on column tasks_substring.unique_id is 'Идентификатор записи. Первичный ключ';

comment on column tasks_substring.uuid is 'UUID записи';

comment on column tasks_substring.created_timestamp is 'Дата и время создания записи';

comment on column tasks_substring.modified_timestamp is 'Дата и время последнего изменения записи';

comment on column tasks_substring.is_deleted is 'Признак удалённой записи';

create table tasks_square
(
    unique_id          bigint identity primary key,
    uuid               uuid not null,
    created_timestamp  datetime default CURRENT_DATE,
    modified_timestamp datetime default CURRENT_DATE,
    is_deleted         boolean default false
);

comment on table tasks_square is 'Таблица, содержащая задачи квадрата';

comment on column tasks_square.unique_id is 'Идентификатор записи. Первичный ключ';

comment on column tasks_square.uuid is 'UUID записи';

comment on column tasks_square.created_timestamp is 'Дата и время создания записи';

comment on column tasks_square.modified_timestamp is 'Дата и время последнего изменения записи';

comment on column tasks_square.is_deleted is 'Признак удалённой записи';

create table task_square_elements
(
    unique_id          bigint identity primary key,
    uuid               uuid not null,
    created_timestamp  datetime default CURRENT_DATE,
    modified_timestamp datetime default CURRENT_DATE,
    is_deleted         boolean default false,
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
    unique_id          bigint identity primary key,
    uuid               uuid not null,
    created_timestamp  datetime default CURRENT_DATE,
    modified_timestamp datetime default CURRENT_DATE,
    is_deleted         boolean default false,
    string_array_entry varchar(8000),
    element_type       bigint not null
        constraint task_substring_elements_element_type_fkey
            references dict_task_substring_element_types (code)
            on update restrict on delete restrict,
    task_ref           bigint not null
        constraint task_substring_elements_task_ref_fkey
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

insert into dict_task_substring_element_types (uuid, code, name, description)
VALUES ('921825f9-4b97-400d-8f31-f26cdae89e8b', 1, 'firstArrElem', 'Входной массив а1 (подстроки)'),
       ('c4cbfb73-3ccd-434b-92c5-be1f5c9e3d6c', 2, 'secondArrElem', 'Входной массив а2 (строки)');