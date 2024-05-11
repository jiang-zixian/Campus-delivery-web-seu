-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('骑手申请', '2000', '1', 'crider', 'application/crider/index', 1, 0, 'C', '0', '0', 'application:crider:list', '#', 'admin', sysdate(), '', null, '骑手申请菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('骑手申请查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'application:crider:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('骑手申请新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'application:crider:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('骑手申请修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'application:crider:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('骑手申请删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'application:crider:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('骑手申请导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'application:crider:export',       '#', 'admin', sysdate(), '', null, '');