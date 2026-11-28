{{- define "kafka.name" -}}
{{- .Chart.Name | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "kafka.fullname" -}}
{{- if .Values.fullnameOverride -}}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- $name := include "kafka.name" . -}}
{{- if contains $name .Release.Name -}}
{{- .Release.Name | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" -}}
{{- end -}}
{{- end -}}
{{- end -}}

{{- define "kafka.labels" -}}
helm.sh/chart: {{ printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{ include "kafka.selectorLabels" . }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end -}}

{{- define "kafka.selectorLabels" -}}
app.kubernetes.io/name: {{ include "kafka.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end -}}

{{- define "kafka.rf" -}}
{{- if kindIs "invalid" .Values.config.replicationFactor -}}
{{- min (int .Values.replicaCount) 3 -}}
{{- else -}}
{{- .Values.config.replicationFactor -}}
{{- end -}}
{{- end -}}

{{- define "kafka.isr" -}}
{{- if kindIs "invalid" .Values.config.minInsyncReplicas -}}
{{- if ge (int (include "kafka.rf" .)) 2 -}}2{{- else -}}1{{- end -}}
{{- else -}}
{{- .Values.config.minInsyncReplicas -}}
{{- end -}}
{{- end -}}


{{- define "kafka.secretName" -}}
{{- if and .Values.auth .Values.auth.existingSecret -}}
{{- .Values.auth.existingSecret -}}
{{- else -}}
{{- include "kafka.fullname" . -}}
{{- end -}}
{{- end -}}

{{- define "kafka.configMapName" -}}
{{- if and .Values.config .Values.config.existingConfigMap -}}
{{- .Values.config.existingConfigMap -}}
{{- else -}}
{{- include "kafka.fullname" . -}}
{{- end -}}
{{- end -}}
