List<${origClassName}> origs = new ArrayList<${origClassName}>();
List<${destClassName}> dests = new ArrayList<${destClassName}>();
for (${origClassName} orig : origs) {
	${destClassName} dest = new ${destClassName}();
	dests.add(dest);
	<#list fiedMapper?keys as key> 
	dest.set${key?cap_first}(orig.get${fiedMapper[key]?cap_first}());
    </#list>
}