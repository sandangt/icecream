{{/* Expand the name of the chart. */}}
{{- define "spring-service.name" -}}
{{- .Values.nameOverride | default .Chart.Name | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/* Full name used for most resources. */}}
{{- define "spring-service.fullname" -}}
{{- if .Values.fullnameOverride -}}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- .Values.nameOverride | default .Chart.Name | trunc 63 | trimSuffix "-" -}}
{{- end -}}
{{- end -}}

{{/* Common labels */}}
{{- define "spring-service.labels" -}}
app.kubernetes.io/name: {{ include "spring-service.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
app.kubernetes.io/part-of: icecream
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end -}}

{{/* Selector labels */}}
{{- define "spring-service.selectorLabels" -}}
app.kubernetes.io/name: {{ include "spring-service.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end -}}

{{/* Service account name */}}
{{- define "spring-service.serviceAccountName" -}}
{{- if .Values.serviceAccount.create -}}
{{ default (include "spring-service.fullname" .) .Values.serviceAccount.name }}
{{- else -}}
{{ default "default" .Values.serviceAccount.name }}
{{- end -}}
{{- end -}}
