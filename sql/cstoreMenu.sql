-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家申请', '2000', '1', 'cstore', 'application/cstore/index', 1, 0, 'C', '0', '0', 'application:cstore:list', '#', 'admin', sysdate(), '', null, '商家申请菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家申请查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'application:cstore:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家申请新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'application:cstore:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家申请修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'application:cstore:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家申请删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'application:cstore:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家申请导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'application:cstore:export',       '#', 'admin', sysdate(), '', null, '');