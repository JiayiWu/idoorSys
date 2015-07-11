package com.idoorSys.controller;

import java.util.Map;

public interface IdoorController {

	String MAPPING_LIST = "list";
	String MAPPING_DELETE = "delete/{id}";
	String MAPPING_UPDATE = "update";
	String MAPPING_ADD = "add";
	String MAPPING_PAGE_EDIT = "page/edit/{id}";
	String MAPPIND_PAGE_ADD = "page/add";
	String MAPPING_FIND_BY_EXAMPLE = "findByExample";
	String MAPPING_FIND_BY_EXAMPLE_JSON = "findByExampleJson";

	String LIST_PAGE = "list";
	String EDIT_PAGE = "edit";
	String ADD_PAGE = "add";
	String DONE_PAGE = "ajaxDone";
	String FAIL_PAGE = "ajaxFail";

	public abstract String list(Map<String, Object> model);

	public abstract String delete(int id, Map<String, Object> model);

	public abstract String pageEdit(int id, Map<String, Object> model);

	public abstract String pageAdd(Map<String, Object> model);

}