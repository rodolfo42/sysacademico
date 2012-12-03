// This script downloaded from www.JavaScriptBank.com
/*****
JsSimpleDateFormat v1.0 (20080509)
This library is for formatting and parsing date time

Copyright (C) 2008 AT Mulyana (atmulyana@yahoo.com)

This library is free software; you can redistribute it and/or modify it
under the terms of the GNU Lesser General Public License version 2.1
See http://gnu.org/licenses/lgpl.html

Send the bug report to 'atmulyana@yahoo.com'
*****/
Function.prototype.__extends__ = function(fParent,oExtMembers) {
	this.prototype = new fParent();
	for (m in oExtMembers) this.prototype[m] = oExtMembers[m];
}

function JsDateFormatSymbols(sLocale) {
	if (!JsDateFormatSymbols.__symbols__[sLocale]) sLocale = 'en';
	var oSymbols = JsDateFormatSymbols.__symbols__[sLocale];
	for (p in oSymbols) this['_'+p] = oSymbols[p];
}
JsDateFormatSymbols.prototype = {
getAmPmStrings: function() {
	return this._amPmStrings;
},
getEras: function() {
	return this._eras;
},
getMonths: function() {
	return this._months;
},
getShortMonths: function() {
	return this._shortMonths;
},
getShortWeekdays: function() {
	return this._shortWeekdays;
},
getWeekdays: function() {
	return this._weekdays;
},
setAmPmStrings: function(arAmPmStrings) {
	this._amPmStrings = arAmPmStrings;
},
setEras: function(arEras) {
	this._eras = arEras;
},
setMonths: function(arMonths) {
	return this._months = arMonths;
},
setShortMonths: function(arShortMonths) {
	return this._shortMonths = arShortMonths;
},
setShortWeekdays: function(arShortWeekdays) {
	return this._shortWeekdays = arShortWeekdays;
},
setWeekdays: function(arWeekdays) {
	return this._weekdays = arWeekdays;
}
};
JsDateFormatSymbols.__symbols__ = {
en: {
	amPmStrings: ['AM','PM'],
	eras: ['AD','BC'],
	months: ['January','February','March','April','May','June','July','August','September','October','November','December'],
	shortMonths: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'],
	shortWeekdays: ['Sun','Mon','Tue','Wed','Thu','Fri','Sat'],
	weekdays: ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday']
},
id: {
	amPmStrings: ['AM','PM'],
	eras: ['M','SM'],
	months: ['Januari','Februari','Maret','April','Mei','Juni','Juli','Agustus','September','Oktober','Nopember','Desember'],
	shortMonths: ['Jan','Feb','Mar','Apr','Mei','Jun','Jul','Agu','Sep','Okt','Nop','Des'],
	shortWeekdays: ['Min','Sen','Sel','Rab','Kam','Jum','Sab'],
	weekdays: ['Minggu','Senin','Selasa','Rabu','Kamis','Jumat','Sabtu']
}
};

function JsSimpleDateFormat(sPattern,param) {
	this._arPtn = [];
	this._ptn = null;
	this.flexWhiteSpace = false;
	if (sPattern) this.applyPattern(sPattern);
	else this.applyPattern(this._getDefaultPattern());
	if (param) {
		if (param instanceof JsDateFormatSymbols) this.setDateFormatSymbols(param);
		else this.setDateFormatSymbols(new JsDateFormatSymbols(param));
	} else {
		this.setDateFormatSymbols(new JsDateFormatSymbols('en'));
	}
	
	var oStDt = new Date();
	try {
		oStDt.setFullYear(oStDt.getFullYear()-80); //See if error prior to 1970 GMT
	} catch (e) {
		oStDt = new Date(0);
	}
	this.set2DigitYearStart(oStDt);
}
JsSimpleDateFormat._Base = function() {
}
JsSimpleDateFormat._Base.prototype = {
isNumber: function() {
	return false;
},
parse: function(s,isNN) {
	return -1;
},
toStr: function() {
	return "";
}
}

JsSimpleDateFormat._Str = function(sInitVal) {
	JsSimpleDateFormat._Base.call(this);
	this._vals = [];
	if (sInitVal) this.append(sInitVal);
}
JsSimpleDateFormat._Str.__extends__(JsSimpleDateFormat._Base, {
flexWhiteSpace: false,
append: function(s) {
	this._vals.push(s);
},
parse: function(s,isNN) {
	var sVal = this.toStr();
	if (this.flexWhiteSpace) {
		var sRe = sVal.replace(/\s+/g," ");
		if (sRe == " ") sRe = "\\s+";
		else sRe = "\\s*" + sRe.replace(/^\s+/,'').replace(/\s+$/,'').replace(/([^a-zA-Z0-9\s])/g,"\\$1").replace(/\s+/g,"\\s*") + "\\s*";
		var reVal = new RegExp("^("+sRe+")");
		if (reVal.test(s)) return RegExp.$1.length;
	} else {
		if (s.indexOf(sVal) == 0) return sVal.length;
	}
	return -1;
},
toStr: function() {
	return this._vals.join("");
}
});

JsSimpleDateFormat._Ltr = function() {
	JsSimpleDateFormat._Base.call(this);
	this._count = 1;
	this._parseVal = parseInt("NaN");
}
JsSimpleDateFormat._Ltr.__extends__(JsSimpleDateFormat._Base, {
name: "",
dt: new Date(),
fmtSb: new JsDateFormatSymbols('en'),
addCount: function() {
	this._count++;
},
applyParseValue: function(oDate,oFields) {
	return oDate;
},
getParseValue: function() {
	return this._parseVal;
},
getValue: function() {
	return -1;
}
});

JsSimpleDateFormat._Text = function() {
	JsSimpleDateFormat._Ltr.call(this);
}
JsSimpleDateFormat._Text.__extends__(JsSimpleDateFormat._Ltr, {
getIndex: function() {
	return -1;
},
getLong: function() {
	var i = this.getIndex(), arVals = this.getLongValues();
	if (i >= 0 && i < arVals.length) return arVals[i];
	return "";
},
getLongValues: function() {
	return [];
},
getShort: function() {
	var i = this.getIndex(), arVals = this.getShortValues();
	if (i >= 0 && i < arVals.length) return arVals[i];
	return "";
},
getShortValues: function() {
	return [];
},
getValue: function() {
	return this.getIndex();
},
parse: function(s,isNN) {
	this._parseVal = parseInt("NaN");
	var arLong = this.getLongValues(), arShort = this.getShortValues();
	var re = new RegExp("^("+arLong.join("|")+"|"+arShort.join("|")+")", "i");
	if (!re.test(s)) return -1;
	var sVal = RegExp.$1.toUpperCase();
	for (var i=0; i<arLong.length; i++) if (sVal == arLong[i].toUpperCase()) {
		this._parseVal = i;
		return sVal.length;
	}
	for (var i=0; i<arShort.length; i++) if (sVal == arShort[i].toUpperCase()) {
		this._parseVal = i;
		return sVal.length;
	}
},
toStr: function() {
	if (this._count < 4) return this.getShort();
	return this.getLong();
}
});

JsSimpleDateFormat._Number = function() {
	JsSimpleDateFormat._Ltr.call(this);
}
JsSimpleDateFormat._Number.__extends__(JsSimpleDateFormat._Ltr, {
getNumber: function() {
	return this.getValue();
},
isNumber: function() {
	return true;
},
isValidVal: function(iVal) {
	return true;
},
parse: function(s,isNN) {
	this._parseVal = "";
	var i = 0, c = s.charAt(0), sVal = "";;
	if (isNN) while(i < this._count && c >= '0' && c <= '9') {
		sVal += c;
		if (++i < s.length) c = s.charAt(i); else break;
	}
	else while(c >= '0' && c <= '9') {
		sVal += c;
		if (++i < s.length) c = s.charAt(i); else break;
	}
	if (i == 0) return -1;
	var iVal = parseInt(sVal, 10);
	if (this.isValidVal(iVal)) this._parseVal = iVal; else return -1;
	return i;
},
toStr: function() {
	var sVal = this.getNumber()+"", s = "";
	if (sVal.charAt(0) == '-') { sVal = sVal.substr(1); s = "-"; }
	while (sVal.length < this._count) sVal = "0" + sVal;
	return s+sVal;
}
});

JsSimpleDateFormat._Month = function() {
	JsSimpleDateFormat._Text.call(this);
}
JsSimpleDateFormat._Month.__extends__(JsSimpleDateFormat._Text, {
name: "month",
isNumber: function() {
	if (this._count < 3) return true; else return false;
},
isValidVal: function(iVal) {
	return iVal >= 1 && iVal <= 12;
},
parse: function(s,isNN) {
	if (this._count < 3) return JsSimpleDateFormat._Number.prototype.parse.call(this,s,isNN);
	return JsSimpleDateFormat._Text.prototype.parse.call(this,s,isNN);
},
toStr: function() {
	if (this._count < 3) return JsSimpleDateFormat._Number.prototype.toStr.call(this);
	return JsSimpleDateFormat._Text.prototype.toStr.call(this);
}
});

JsSimpleDateFormat._Year = function() {
	JsSimpleDateFormat._Number.call(this);
}
JsSimpleDateFormat._Year.__extends__(JsSimpleDateFormat._Number, {
name: "year",
stC: 1900,
stY: 1970,
parse: function(s,isNN) {
	var j = 0;
	if (s.charAt(0) == '-') {
		s = s.substr(1);
		j++;
	}
	var i = JsSimpleDateFormat._Number.prototype.parse.call(this,s,isNN);
	if (i == -1) return -1;
	if (j > 0) this._parseVal = -this._parseVal;
	if (this._count < 3 && this._parseVal > 0 && i == 2) {
		var iY = this.stC + this._parseVal;
		if (iY <= this.stY) iY += 100;
		this._parseVal = iY;
	}
	return i+j;
},
toStr: function() {
	if (this._count < 4) {
		var sVal = (this.getNumber() % 100) + "";
		if (sVal.length < 2) return "0"+sVal;
		return sVal;
	}
	return JsSimpleDateFormat._Number.prototype.toStr.call(this);
}
});

JsSimpleDateFormat._ltr = {};
JsSimpleDateFormat._ltr.G = function() {
	JsSimpleDateFormat._Text.call(this);
}
JsSimpleDateFormat._ltr.G.__extends__(JsSimpleDateFormat._Text, {
name: "era",
getIndex: function() {
	return (this.dt.getFullYear() > 0 ? 0 : 1);
},
getLongValues: function() {
	return this.fmtSb.getEras();
},
getShortValues: function() {
	return this.getLongValues();
}
});

JsSimpleDateFormat._ltr.y = function() {
	JsSimpleDateFormat._Year.call(this);
}
JsSimpleDateFormat._ltr.y.__extends__(JsSimpleDateFormat._Year, {
applyParseValue: function(oDate,oFields) {
	if (oFields.era) {
		if (oFields.era.getParseValue()==0 && this._parseVal<=0) return null;
		if (oFields.era.getParseValue()==1 && this._parseVal>0) this._parseVal = -this._parseVal+1;
	}
	oDate.setFullYear(this._parseVal);
	return oDate;
},
getNumber: function() {
	var iVal = this.getValue();
	return (iVal <= 0) ? (-iVal + 1) : iVal;
},
getValue: function() {
	return this.dt.getFullYear();
}
});

JsSimpleDateFormat._ltr.M = function() {
	JsSimpleDateFormat._Month.call(this);
}
JsSimpleDateFormat._ltr.M.__extends__(JsSimpleDateFormat._Month, {
applyParseValue: function(oDate,oFields) {
	var iVal = this.getParseValue(), iD = oDate.getDate();
	oDate.setMonth(iVal);
	while (oDate.getMonth() != iVal) {
		oDate.setDate(--iD); //Find the last day of the month
		oDate.setMonth(iVal);
	}
	return oDate;
},
getIndex: function() {
	return this.dt.getMonth();
},
getLongValues: function() {
	return this.fmtSb.getMonths();
},
getNumber: function() {
	return this.dt.getMonth() + 1;
},
getParseValue: function() {
	return this._count < 3 ? this._parseVal-1 : this._parseVal;
},
getShortValues: function() {
	return this.fmtSb.getShortMonths();
}
});

JsSimpleDateFormat._ltr.D = function() {
	JsSimpleDateFormat._Number.call(this);
}
JsSimpleDateFormat._ltr.D.__extends__(JsSimpleDateFormat._Number, {
_ends: [31,28,31,30,31,30,31,31,30,31,30,31],
name: "dayOfYear",
_checkLeapYear: function(oDate) {
	//I dont trust year % 4
	var oDt = new Date(oDate.getTime());
	oDt.setDate(1); oDt.setMonth(1); oDt.setDate(29);
	if (oDt.getDate() == 29) this._ends[1] = 29; else this._ends[1] = 28;
},	
applyParseValue: function(oDate,oFields) {
	if (oFields.year) if (oFields.year.applyParseValue(oDate,oFields) == null) return null;
	this._checkLeapYear(oDate);
	var arEnds = this._ends, iD = this.getParseValue(), iM = 0;
	while (iD > arEnds[iM] && iM < arEnds.length) iD -= arEnds[iM++];
	if (iM >= arEnds.length) return null;
	oDate.setDate(1);
	oDate.setMonth(iM);
	oDate.setDate(iD);
	return oDate;
},
getDay: function() {
	this._checkLeapYear(this.dt);
	var arEnds = this._ends;
	var iMonth = this.dt.getMonth(), iDay = 0;
	for (var i=0; i<iMonth; i++) iDay += arEnds[i];
	return iDay + this.dt.getDate();
},
getValue: function() {
	return this.getDay();
},
isValidVal: function(iVal) {
	return iVal >= 1 && iVal <= 366;
}
});

JsSimpleDateFormat._ltr.d = function() {
	JsSimpleDateFormat._ltr.D.call(this);
}
JsSimpleDateFormat._ltr.d.__extends__(JsSimpleDateFormat._ltr.D, {
name: "day",
applyParseValue: function(oDate,oFields) {
	if (oFields.year) if (oFields.year.applyParseValue(oDate,oFields) == null) return null;
	this._checkLeapYear(oDate);
	if (oFields.month) if (oFields.month.applyParseValue(oDate,oFields) == null) return null;
	var arEnds = this._ends, iD = this.getParseValue(), iM = oDate.getMonth();
	if (iD < 1 || iD > arEnds[iM]) return null;
	oDate.setDate(iD);
	return oDate;
},
getDay: function() {
	return this.dt.getDate();
},
isValidVal: function(iVal) {
	return iVal >= 1 && iVal <= 31;
}
});

JsSimpleDateFormat._ltr.w = function() {
	JsSimpleDateFormat._ltr.D.call(this);
}
JsSimpleDateFormat._ltr.w.__extends__(JsSimpleDateFormat._ltr.D, {
name: "weekOfYear",
_resetMonth: function(oDate) {
	oDate.setMonth(0);
},
applyParseValue: function(oDate,oFields) {
	oDate.setDate(1);
	this._resetMonth(oDate);
	oDate.setTime(oDate.getTime() - oDate.getDay()*86400000 + (this._parseVal-1)*7*86400000); //86400000 == ms per day
	return oDate;
},
getParseValue: function() {
	return this.getValue();
},
getValue: function() {
	return this.getWeek();
},
getWeek: function() {
	/*** It's my magic formula for getting the week number. Hope no bug at all. ***/
	var iDay = this.getDay();
	var iWeek = Math.ceil(iDay/7);
	iDay = iDay % 7;
	iDay = (iDay ? iDay : 7) - 1;
	return ((this.dt.getDay() < iDay) ? (iWeek+1) : iWeek);
},
isValidVal: function(iVal) {
	return iVal >= 1 && iVal <= 54;
}
});

JsSimpleDateFormat._ltr.W = function() {
	JsSimpleDateFormat._ltr.w.call(this);
}
JsSimpleDateFormat._ltr.W.__extends__(JsSimpleDateFormat._ltr.w, {
name: "weekOfMonth",
_resetMonth: function(oDate) {
},
getDay: function() {
	return this.dt.getDate();
},
isValidVal: function(iVal) {
	return iVal >= 1 && iVal <= 6;
}
});

JsSimpleDateFormat._ltr.F = function() {
	JsSimpleDateFormat._Number.call(this);
}
JsSimpleDateFormat._ltr.F.__extends__(JsSimpleDateFormat._Number, {
name: "dayOfWeekInMonth",
applyParseValue: function(oDate,oFields) {
	oDate.setDate((this.getParseValue()-1)*7 + 1);
	return oDate;
},
isValidVal: function(iVal) {
	return iVal >= 1 && iVal <= 5;
},
getValue: function() {
	return Math.ceil(this.dt.getDate() / 7);
}
});

JsSimpleDateFormat._ltr.E = function() {
	JsSimpleDateFormat._Text.call(this);
}
JsSimpleDateFormat._ltr.E.__extends__(JsSimpleDateFormat._Text, {
name: "dayOfWeek",
applyParseValue: function(oDate,oFields) {
	oDate.setDate(1);
	var iD = oDate.getDay();
	var iVal = (this._parseVal < iD) ? (this._parseVal+7) : this._parseVal;
	oDate.setTime(oDate.getTime() + (iVal - iD)*86400000);
	return oDate;
},
getIndex: function() {
	return this.dt.getDay();
},
getLongValues: function() {
	return this.fmtSb.getWeekdays();
},
getShortValues: function() {
	return this.fmtSb.getShortWeekdays();
}
});

JsSimpleDateFormat._ltr.a = function() {
	JsSimpleDateFormat._Text.call(this);
}
JsSimpleDateFormat._ltr.a.__extends__(JsSimpleDateFormat._Text, {
name: "ampm",
getIndex: function() {
	return (this.dt.getHours() < 12 ? 0 : 1);
},
getLongValues: function() {
	return this.fmtSb.getAmPmStrings();
},
getShortValues: function() {
	return this.getLongValues();
}
});

JsSimpleDateFormat._ltr.H = function() {
	JsSimpleDateFormat._Number.call(this);
}
JsSimpleDateFormat._ltr.H.__extends__(JsSimpleDateFormat._Number, {
name: "hour",
applyParseValue: function(oDate,oFields) {
	oDate.setHours(this.getParseValue());
	return oDate;
},
getValue: function() {
	return this.dt.getHours();
},
isValidVal: function(iVal) {
	return iVal >= 0 && iVal <= 23;
}
});

JsSimpleDateFormat._ltr.k = function() {
	JsSimpleDateFormat._ltr.H.call(this);
}
JsSimpleDateFormat._ltr.k.__extends__(JsSimpleDateFormat._ltr.H, {
getParseValue: function() {
	return this._parseVal == 24 ? 0 : this._parseVal;
},
getNumber: function() {
	var iH = this.dt.getHours();
	return (iH > 0 ? iH : 24);
},
isValidVal: function(iVal) {
	return iVal >= 1 && iVal <= 24;
}
});

JsSimpleDateFormat._ltr.K = function() {
	JsSimpleDateFormat._Number.call(this);
}
JsSimpleDateFormat._ltr.K.__extends__(JsSimpleDateFormat._Number, {
name: "h12",
applyParseValue: function(oDate,oFields) {
	var iVal = this.getParseValue();
	if (oFields.ampm && oFields.ampm.getParseValue() == 1) iVal += 12;
	oDate.setHours(iVal);
	return oDate;
},
getValue: function() {
	return this.dt.getHours() % 12;
},
isValidVal: function(iVal) {
	return iVal >= 0 && iVal <= 11;
}
});

JsSimpleDateFormat._ltr.h = function() {
	JsSimpleDateFormat._ltr.K.call(this);
}
JsSimpleDateFormat._ltr.h.__extends__(JsSimpleDateFormat._ltr.K, {
getParseValue: function() {
	return this._parseVal == 12 ? 0 : this._parseVal;
},
getNumber: function() {
	var iH = this.dt.getHours() % 12;
	return (iH > 0 ? iH : 12);
},
isValidVal: function(iVal) {
	return iVal >= 1 && iVal <= 12;
}
});

JsSimpleDateFormat._ltr.m = function() {
	JsSimpleDateFormat._Number.call(this);
}
JsSimpleDateFormat._ltr.m.__extends__(JsSimpleDateFormat._Number, {
name: "minute",
applyParseValue: function(oDate,oFields) {
	oDate.setMinutes(this.getParseValue());
	return oDate;
},
getValue: function() {
	return this.dt.getMinutes();
},
isValidVal: function(iVal) {
	return iVal >= 0 && iVal <= 59;
}
});

JsSimpleDateFormat._ltr.s = function() {
	JsSimpleDateFormat._Number.call(this);
}
JsSimpleDateFormat._ltr.s.__extends__(JsSimpleDateFormat._Number, {
name: "second",
applyParseValue: function(oDate,oFields) {
	oDate.setSeconds(this.getParseValue());
	return oDate;
},
getValue: function() {
	return this.dt.getSeconds();
},
isValidVal: function(iVal) {
	return iVal >= 0 && iVal <= 59;
}
});

JsSimpleDateFormat._ltr.S = function() {
	JsSimpleDateFormat._Number.call(this);
}
JsSimpleDateFormat._ltr.S.__extends__(JsSimpleDateFormat._Number, {
name: "ms",
applyParseValue: function(oDate,oFields) {
	oDate.setMilliseconds(this.getParseValue());
	return oDate;
},
getValue: function() {
	return this.dt.getMilliseconds();
},
isValidVal: function(iVal) {
	return iVal >= 0 && iVal <= 999;
}
});

JsSimpleDateFormat.prototype = {
_getDefaultPattern: function() {
	return "dd MMMM yyyy hh:mm a";
},
_getInitDate: function() {
	var oDt = new Date(0);
	oDt.setTime(oDt.getTime() + oDt.getTimezoneOffset()*60000);
	return oDt;
},
applyPattern: function(sPattern) {
	this._arPtn = [];
	var oLtr = JsSimpleDateFormat._ltr;
	var s=new JsSimpleDateFormat._Str(""), c='', oPtn=null, clsPtn, isQ=false, i=-1;
	while (++i < sPattern.length) {
		var c1 = sPattern.charAt(i);
		if (c1 == "'") {
			if (i < sPattern.length-1 && sPattern.charAt(i+1) == "'") {
				s.append("'");
				i++;
			} else {
				isQ = !isQ;
			}
			c = '';
		} else if (isQ) {
			s.append(c1);
		} else if (c1 == c) {
			oPtn.addCount();
		} else if (clsPtn = oLtr[c1]) {
			oPtn = new clsPtn();
			if (s.toStr() != "") this._arPtn.push(s);
			s = new JsSimpleDateFormat._Str("");
			this._arPtn.push(oPtn);
			c = c1;
		} else {
			s.append(c1);
			c = '';
		}
	}
	if (s.toStr() != "") this._arPtn.push(s);
	this._ptn = sPattern;
},
format: function(oDate) {
	JsSimpleDateFormat._Ltr.prototype.fmtSb = this._fmtSb;
	JsSimpleDateFormat._Ltr.prototype.dt = oDate;
	var s = "", arPtn = this._arPtn;
	for (var i=0; i<arPtn.length; i++) s += arPtn[i].toStr();
	return s;
},
get2DigitYearStart: function() {
	return this._stDt;
},
getDateFormatSymbols: function() {
	return this._fmtSb;
},
parse: function(s,oPos) {
	JsSimpleDateFormat._Ltr.prototype.fmtSb = this._fmtSb;
	JsSimpleDateFormat._Str.prototype.flexWhiteSpace = this.flexWhiteSpace;
	JsSimpleDateFormat._Year.prototype.stY = this._stY;
	JsSimpleDateFormat._Year.prototype.stC = this._stC;
	
	if (!oPos) oPos = {index:0, errorIndex:-1};
	var i = oPos.index, j = 0, arPtn = this._arPtn, oFields = {};
	while (j < arPtn.length) {
		var oPtn = arPtn[j++];
		var isNN = (j<arPtn.length) ? arPtn[j].isNumber() : false;
		var k = oPtn.parse(s.substr(i),isNN);
		if (k == -1) {
			oPos.errorIndex = i;
			return null;
		}
		if (oPtn instanceof JsSimpleDateFormat._Ltr) {
			var sFN = oPtn.name;
			if (oFields[sFN]) {
				if (oFields[sFN].getParseValue() != oPtn.getParseValue()) {
					oPos.errorIndex = i;
					return null;
				}
			} else {
				oFields[sFN] = oPtn;
			}
		}
		i += k;
	}
	
	var oDate = this._getInitDate();
	var arFN = ["year","month","dayOfWeek","dayOfWeekInMonth","weekOfMonth","weekOfYear","dayOfYear",
		"day","hour","h12","minute","second","ms"];
	for (j=0; j<arFN.length; j++) {
		var sFN = arFN[j];
		if (oFields[sFN]) if (oFields[sFN].applyParseValue(oDate,oFields) == null) {
			oPos.errorIndex = oPos.index + i;
			return null;
		}
	}
	
	JsSimpleDateFormat._Ltr.prototype.dt = oDate;
	for (var sFN in oFields) if (oFields[sFN].getParseValue() != oFields[sFN].getValue()) {
		oPos.errorIndex = oPos.index + i;
		return null;
	}
	oPos.index += i;
	return oDate;
},
set2DigitYearStart: function(oStartDate) {
	this._stDt = oStartDate;
	var iY = Math.abs(oStartDate.getFullYear());
	this._stY = iY;
	this._stC = iY - (iY%100);
},
setDateFormatSymbols: function(oFormatSymbols) {
	this._fmtSb = oFormatSymbols;
},
toPattern: function() {
	return this._ptn;
}
};
