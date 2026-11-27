{{- define "dbz.name" -}}
{{- .Chart.Name | trunc 63 | trimSuffix "-" }}
{{- end }}

{{- define "dbz.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else if contains .Chart.Name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name .Chart.Name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}

{{- define "dbz.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{- define "dbz.labels" -}}
helm.sh/chart: {{ include "dbz.chart" . }}
{{ include "dbz.selectorLabels" . }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{- define "dbz.selectorLabels" -}}
app.kubernetes.io/name: {{ include "dbz.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{- define "dbz.secretName" -}}
{{- default (include "dbz.fullname" .) .Values.auth.existingSecret }}
{{- end }}

{{- define "dbz.configMapName" -}}
{{- default (include "dbz.fullname" .) .Values.config.existingConfigMap }}
{{- end }}
