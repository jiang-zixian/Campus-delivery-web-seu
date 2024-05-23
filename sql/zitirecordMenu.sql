-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('自提订单', '2093', '1', 'zitiRecord', 'record/zitiRecord/index', 1, 0, 'C', '0', '0', 'record:zitiRecord:list', '#', 'admin', sysdate(), '', null, '自提订单菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('自提订单查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'record:zitiRecord:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('自提订单新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'record:zitiRecord:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('自提订单修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'record:zitiRecord:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('自提订单删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'record:zitiRecord:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('自提订单导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'record:zitiRecord:export',       '#', 'admin', sysdate(), '', null, '');